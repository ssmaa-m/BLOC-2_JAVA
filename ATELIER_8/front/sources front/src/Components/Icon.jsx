import React from 'react'

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { Tooltip, Popover } from 'antd'



const Icon = (props) => {

  const { ...newProps } = props

  if (typeof props.icon === "string") {
    const split = props.icon.split(' ')
    newProps.icon = split.length === 1 ? ["fal", props.icon] : split
  }

  if (newProps.color) {
    switch (newProps.color) {
      case 'red': newProps.color = '#f5222d'; break
      case 'volcano': newProps.color = '#fa541c'; break
      case 'orange': newProps.color = '#fa8c16'; break
      case 'gold': newProps.color = '#faad14'; break
      case 'yellow': newProps.color = '#fadb14'; break
      case 'lime': newProps.color = '#a0d911'; break
      case 'green': newProps.color = '#52c41a'; break
      case 'cyan': newProps.color = '#13c2c2'; break
      case 'blue': newProps.color = '#1890ff'; break
      case 'geekblue': newProps.color = '#2f54eb'; break
      case 'purple': newProps.color = '#722ed1'; break
      case 'magenta': newProps.color = '#eb2f96'; break
      case 'grey': newProps.color = '#bfbfbf'; break
      default:
    }
  }

  return <FontAwesomeIcon {...newProps} />

}

export default Icon



Icon.Visibility = ({ visible }) => {
  return visible
    ? <Icon icon="eye" style={{ color: "#52c41a" }} fixedWidth />
    : <Icon icon="eye-slash" style={{ color: "#f5222d" }} fixedWidth />
}

Icon.Boolean = ({ boolean, noError = false }) => {
  return boolean
    ? <Icon icon="check" style={{ color: "#52c41a" }} fixedWidth size="lg" />
    : noError ? <>-</> : <Icon icon="times" style={{ color: "#f5222d" }} fixedWidth size="lg" />
}

Icon.Info = ({ tooltip = null, popover = null, style = null }) => {
  if (tooltip) {
    return <Tooltip title={tooltip}><Icon icon="info-circle" style={style} /></Tooltip>
  }
  if (popover) {
    return <Popover title={popover.title} content={popover.content}><Icon icon="info-circle" style={style} /></Popover>
  }
  return <Icon icon="info-circle" />
}