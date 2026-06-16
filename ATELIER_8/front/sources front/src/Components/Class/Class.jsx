import './Class.css'


const Class = ({ data }) => {

  if (!data) return <></>

  return (
    <table className="uml-class">
      <tbody>
        <tr>
          <td>{data.name}</td>
        </tr>
        <tr>
          <td>
            {data.fields?.map(f => <Field field={f} key={f.name} />)}
          </td>
        </tr>
        <tr>
          <td>
            {data.methods?.map(m => <Method method={m} key={m.name} />)}
          </td>
        </tr>
      </tbody>
    </table>
  )
}

export default Class


const Field = ({ field }) => {
  let classname = ''
  if (field.isStatic) classname += 'static '
  return <div className={classname}>{getVisibility(field.visibility)}{field.name} : {field.type}</div>
}

const Method = ({ method }) => {
  let classname = ''
  if (method.isStatic) classname += 'static '
  if (method.isAbstract) classname += 'abstract '
  return <div className={classname}>{getVisibility(method.visibility)}{method.name}({method.parameters?.join(', ')}) : {method.returnType}</div>
}

const getVisibility = (visiblity) => {
  switch (visiblity) {
    case 'public': return '+'
    case 'private': return '-'
    case 'protected': return '#'
    case 'package': return '~'
    default: return ''
  }
}