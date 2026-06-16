import { useState, useEffect } from 'react'

import './ObjectsDiagram.css'

import axios from 'axios'

import Icon from '../Icon'
import Objects from '../Objects/Objects'

import { Input, Button, Row, Col, Card, Modal } from 'antd'



const ObjectsDiagram = () => {

  const [classname, setClassname] = useState("InstanceGraph1")
  const [objectsData, setObjectsData] = useState(null)
  const [loading, setLoading] = useState(false)

  const loadObjects = () => {
    setLoading(true)
    axios.get("http://localhost:8080/reflect-api/instances?builderclassname=" + classname)
      .then(({ data }) => {
        setLoading(false)
        setObjectsData(data)
      })
      .catch(err => {
        setLoading(false)
        Modal.error({
          title: "Erreur !",
          content: <>
            {err.message}
            <br /><br />
            {err.response?.data
              ? <>
                <strong>Message du serveur : </strong>
                {err.response.data}
              </>
              : <strong>Aucune réponse du serveur... Est-ce qu'il est démarré ?</strong>}
          </>
        })
      })
  }

  useEffect(loadObjects, []) // eslint-disable-line

  return (
    <>
      <Input.Group compact style={{ marginBottom: 30 }}>
        <Input style={{ width: 200 }} placeholder="Builder class" value={classname} onChange={e => setClassname(e.target.value)} />
        <Button type="primary" onClick={loadObjects} loading={loading}>Charger le graphe</Button>
      </Input.Group>

      <Row gutter={32}>
        <Col span={12}>
          <Card title={<><Icon icon="project-diagram" fixedWidth /> Diagramme d'objets</>}>
            <Objects data={objectsData} />
          </Card>
        </Col>
        <Col span={12}>
          <Card title={<><Icon icon="code" fixedWidth /> JSON</>}>
            <pre style={{ fontSize: 12 }}>{JSON.stringify(objectsData, null, 4)}</pre>
          </Card>
        </Col>
      </Row>
    </>
  )
}

export default ObjectsDiagram
