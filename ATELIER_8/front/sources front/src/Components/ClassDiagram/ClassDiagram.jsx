import { useState, useEffect } from 'react'

import './ClassDiagram.css'

import axios from 'axios'

import Icon from '../Icon'
import Class from '../Class/Class'

import { Input, Button, Row, Col, Card, Modal } from 'antd'



const ClassDiagram = () => {

  const [classname, setClassname] = useState("User")
  const [classData, setClassData] = useState(null)
  const [loading, setLoading] = useState(false)

  const loadClass = () => {
    setLoading(true)
    axios.get("http://localhost:8080/reflect-api/classes?classname=" + classname)
      .then(({ data }) => {
        setClassData(data)
        setLoading(false)
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

  useEffect(loadClass, []) // eslint-disable-line

  return (
    <>
      <Input.Group compact style={{ marginBottom: 30 }}>
        <Input style={{ width: 200 }} placeholder="Classe à analyser" value={classname} onChange={e => setClassname(e.target.value)} />
        <Button type="primary" onClick={loadClass} loading={loading}>Charger la classe</Button>
      </Input.Group>

      <Row gutter={32}>
        <Col span={12}>
          <Card title={<><Icon icon="rectangle-landscape" fixedWidth /> Diagramme de classe</>}>
            <Class data={classData} />
          </Card>
        </Col>
        <Col span={12}>
          <Card title={<><Icon icon="code" fixedWidth /> JSON</>}>
            <pre style={{ fontSize: 12 }}>{JSON.stringify(classData, null, 4)}</pre>
          </Card>
        </Col>
      </Row>
    </>
  )
}

export default ClassDiagram
