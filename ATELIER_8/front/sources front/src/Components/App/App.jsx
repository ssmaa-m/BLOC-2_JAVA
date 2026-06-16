import { useState } from 'react'

import './App.css'

import Icon from '../Icon'
import ClassDiagram from '../ClassDiagram/ClassDiagram'
import ObjectsDiagram from '../ObjectsDiagram/ObjectsDiagram'

import { Layout, Menu } from 'antd'

const { Header, Content } = Layout;



const App = () => {

  const [tab, setTab] = useState("class")

  return (
    <Layout className="layout">
      <Header style={{ position: 'fixed', zIndex: 1, width: '100%' }}>
        <Menu theme="dark" mode="horizontal" selectedKeys={[tab]} onSelect={({ key }) => setTab(key)}>
          <Menu.Item key="class" icon={<Icon icon="rectangle-landscape" fixedWidth />}>Diagramme de classe</Menu.Item>
          <Menu.Item key="object" icon={<Icon icon="project-diagram" fixedWidth />}>Diagramme d'objets</Menu.Item>
        </Menu>
      </Header>
      <Content style={{ padding: '94px 30px 30px 30px', minHeight: '100vh' }}>
        {tab === 'class' && <ClassDiagram />}
        {tab === 'object' && <ObjectsDiagram />}
      </Content>
    </Layout>
  )
}

export default App
