import {LineBreakMode} from '@/common/js/const.js'

/**
 * [Datestr 时间戳转字符串格式]
 */
export function socialDateFormat (date) {
  date = new Date(date)
  return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
}

/**
 *  映射tag颜色
 */
export function mapTagColor (index) {
  switch (index % 4) {
    case 0:
      return 'blue'
    case 1:
      return 'green'
    case 2:
      return 'red'
    case 3:
      return 'yellow'
  }
}

/**
 * 树形数据转换
 * @param {*} data
 * @param {*} id
 * @param {*} pid
 */
export function treeDataTranslate (data, id = 'id', pid = 'parentId') {
  var res = []
  var temp = {}
  for (var i = 0; i < data.length; i++) {
    temp[data[i][id]] = data[i]
  }
  for (var k = 0; k < data.length; k++) {
    if (temp[data[k][pid]] && data[k][id] !== data[k][pid]) {
      if (!temp[data[k][pid]]['children']) {
        temp[data[k][pid]]['children'] = []
      }
      if (!temp[data[k][pid]]['_level']) {
        temp[data[k][pid]]['_level'] = 1
      }
      data[k]['_level'] = temp[data[k][pid]]._level + 1
      temp[data[k][pid]]['children'].push(data[k])
    } else {
      res.push(data[k])
    }
  }
  return res
}

export const mixin = {
  filters: {
    // 用于映射标签颜色
    mapTagColor: function (index) {
      return mapTagColor(index)
    },
    // 用于格式化时间的过滤器
    socialDate: function (formatedDate) {
      return socialDateFormat(formatedDate)
    },
    // 去除html标签
    filterHtml: function (richText) {
      return richText.replace(/<.+?>/g, '')
    },
    // 用于处理行尾省略号的过滤器
    textLineBreak: function (text, maxLength, lineBreakMode) {
      if (text === undefined || text === null || text.length === 0) {
        return ''
      }
      if (lineBreakMode === null || lineBreakMode === undefined) {
        lineBreakMode = LineBreakMode.EllipsisTruncatingTail
      }
      switch (lineBreakMode) {
        case LineBreakMode.WrappingTruncatingTail:
          return text.substr(0, maxLength)
        case LineBreakMode.WrappingTruncatingHead:
          return text.substr(-maxLength)
        case LineBreakMode.EllipsisTruncatingTail:
          return text.substr(0, maxLength) + (text.length > maxLength ? '...' : '')
        case LineBreakMode.EllipsisTruncatingMiddle:
          let resultText = text.substr(0, maxLength)
          if (text.length > maxLength) {
            return resultText.substr(0, parseInt(maxLength / 2)) + '...' + resultText.substr(parseInt(maxLength / 2))
          }
          return resultText
        case LineBreakMode.EllipsisTruncatingHead:
          return (text.length > maxLength ? '...' : '') + text.substr(-maxLength)
      }
      return text
    }
  }
}
