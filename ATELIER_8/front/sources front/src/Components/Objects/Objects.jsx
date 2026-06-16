import './Objects.css'


const Objects = ({ data }) => {

  if (!data) return <></>

  return (
    <table className="uml-object">
      <tbody>
        <tr>
          <td>: {data.classname}</td>
        </tr>
        <tr>
          <td>
            {data.fields?.map(f => <Field field={f} key={f.name} />)}
          </td>
        </tr>
      </tbody>
    </table>
  )
}

export default Objects


const Field = ({ field }) => {
  let classname = ''
  if (field.isStatic) classname += 'static '
  return <div className={classname}>{field.name} = {field.value}</div>
}