package space.xiaocai.util;

public class Const {

    public static final String EMPTY = "";

    private static final String FILE_UPLOAD_JS = "function uploadFiles(files) {\n" +
            "    // 遍历文件列表，执行上传操作\n" +
            "    Array.prototype.forEach.call(files, function(file) {\n" +
            "        console.log(file.name); // 输出文件名\n" +
            "        uploadFile(file);\n" +
            "    });\n" +
            "}\n" +
            "\n" +
            "function uploadFile(file){\n" +
            "    // 定义每一段上传的大小，单位为字节\n" +
            "    let partSize = 10 * 1024 * 1024;\n" +
            "\n" +
            "    let fileSize = file.size;\n" +
            "    let offset = 0;\n" +
            "    \n" +
            "    uploadNextPart(file);\n" +
            "\n" +
            "    // 分段上传函数\n" +
            "    function uploadNextPart(file) {\n" +
            "        if (offset >= fileSize) {\n" +
            "            // 文件上传完成\n" +
            "            return;\n" +
            "        }\n" +
            "\n" +
            "        let remainSize = fileSize - offset;\n" +
            "        let uploadSize = Math.min(partSize, remainSize);\n" +
            "\n" +
            "        let formData = new FormData();\n" +
            "        let blob = new Blob([file.slice(offset, offset + uploadSize)]);\n" +
            "        formData.append('file', blob, file.name);\n" +
            "\n" +
            "        // 创建一个XMLHttpRequest对象\n" +
            "        let xhr = new XMLHttpRequest();\n" +
            "        var currentUrl = window.location.href;\n" +
            "        xhr.open('POST', currentUrl);\n" +
            "        //xhr.setRequestHeader('Content-Type', 'multipart/form-data');\n" +
            "        xhr.onload = function() {\n" +
            "            console.log(\"xhr.status:\"+xhr.status);\n" +
            "            if (xhr.status >= 200 && xhr.status < 300) {\n" +
            "                // 分段上传成功，继续上传下一段\n" +
            "                offset += uploadSize;\n" +
            "                setTimeout(function() {\n" +
            "                    uploadNextPart(file);\n" +
            "                  }, 5000);\n" +
            "            } else {\n" +
            "                // 分段上传失败\n" +
            "                console.error(xhr.statusText);\n" +
            "                setTimeout(function() {\n" +
            "                    uploadNextPart(file);\n" +
            "                  }, 5000);\n" +
            "            }\n" +
            "        };\n" +
            "        xhr.onerror = function(evt) {\n" +
            "            console.log('请求错误：', evt);\n" +
            "            // 这里可以进行错误处理，例如提示用户或重新发起请求等\n" +
            "            setTimeout(function() {\n" +
            "                uploadNextPart(file);\n" +
            "              }, 5000);\n" +
            "          };          \n" +
            "        xhr.send(formData);\n" +
            "    }\n" +
            "}\n" +
            "\n";

    public static final String HTML = "<!DOCTYPE html>\n" + "\n" + "<html i18n-values=\"dir:textdirection;lang:language\">\n" + "\n" + "<head>\n" + "<meta charset=\"utf-8\">\n" + "<meta name=\"google\" value=\"notranslate\">\n" + "\n" + "<script>\n" + "function addRow(name, url, isdir, size, date_modified) {\n" + "  if (name == \".\")\n" + "    return;\n" + "\n" + "  var root = document.location.pathname;\n" + "  if (root.substr(-1) !== \"/\")\n" + "    root += \"/\";\n" + "\n" + "  var table = document.getElementById(\"table\");\n" + "  var row = document.createElement(\"tr\");\n" + "  var file_cell = document.createElement(\"td\");\n" + "  var link = document.createElement(\"a\");\n" + "\n" + "  link.className = isdir ? \"icon dir\" : \"icon file\";\n" + "\n" + "  if (name == \"..\") {\n" + "    link.href = root + \"..\";\n" + "    link.innerText = document.getElementById(\"parentDirText\").innerText;\n" + "    link.className = \"icon up\";\n" + "    size = \"\";\n" + "    date_modified = \"\";\n" + "  } else {\n" + "    if (isdir) {\n" + "      name = name + \"/\";\n" + "      url = url + \"/\";\n" + "      size = \"\";\n" + "    } else {\n" + "      link.draggable = \"true\";\n" + "      link.addEventListener(\"dragstart\", onDragStart, false);\n" + "    }\n" + "    link.innerText = name;\n" + "    link.href = root + url;\n" + "  }\n" + "  file_cell.appendChild(link);\n" + "\n" + "  row.appendChild(file_cell);\n" + "  row.appendChild(createCell(size));\n" + "  row.appendChild(createCell(date_modified));\n" + "\n" + "  table.appendChild(row);\n" + "}\n" + "\n" + "function onDragStart(e) {\n" + "  var el = e.srcElement;\n" + "  var name = el.innerText.replace(\":\", \"\");\n" + "  var download_url_data = \"application/octet-stream:\" + name + \":\" + el.href;\n" + "  e.dataTransfer.setData(\"DownloadURL\", download_url_data);\n" + "  e.dataTransfer.effectAllowed = \"copy\";\n" + "}\n" + "\n" + "function createCell(text) {\n" + "  var cell = document.createElement(\"td\");\n" + "  cell.setAttribute(\"class\", \"detailsColumn\");\n" + "  cell.innerText = text;\n" + "  return cell;\n" + "}\n" + "\n" + "function start(location) {\n" + "  var header = document.getElementById(\"header\");\n" + "  header.innerText = header.innerText.replace(\"LOCATION\", location);\n" + "\n" + "  document.getElementById(\"title\").innerText = header.innerText;\n" + "}\n" + "\n" + "function onListingParsingError() {\n" + "  var box = document.getElementById(\"listingParsingErrorBox\");\n" + "  box.innerHTML = box.innerHTML.replace(\"LOCATION\", encodeURI(document.location)\n" + "      + \"?raw\");\n" + "  box.style.display = \"block\";\n" + "}\n" + "</script>\n" + "\n" + "<style>\n" + "\n" + "  h1 {\n" + "    border-bottom: 1px solid #c0c0c0;\n" + "    margin-bottom: 10px;\n" + "    padding-bottom: 10px;\n" + "    white-space: nowrap;\n" + "  }\n" + "\n" + "  table {\n" + "    border-collapse: collapse;\n" + "  }\n" + "\n" + "  tr.header {\n" + "    font-weight: bold;\n" + "  }\n" + "\n" + "  td.detailsColumn {\n" + "    -webkit-padding-start: 2em;\n" + "    padding-inline-start: 2em;\n" + "    text-align: end;\n" + "    white-space: nowrap;\n" + "  }\n" + "\n" + "  a.icon {\n" + "    -webkit-padding-start: 1.5em;\n" + "    padding-inline-start: 1.5em;\n" + "    text-decoration: none;\n" + "    padding-left: 20px;\n" + "  }\n" + "\n" + "  a.icon:hover {\n" + "    text-decoration: underline;\n" + "  }\n" + "\n" + "  a.file {\n" + "    background : url(\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAIAAACQkWg2AAAABnRSTlMAAAAAAABupgeRAAABHUlEQVR42o2RMW7DIBiF3498iHRJD5JKHurL+CRVBp+i2T16tTynF2gO0KSb5ZrBBl4HHDBuK/WXACH4eO9/CAAAbdvijzLGNE1TVZXfZuHg6XCAQESAZXbOKaXO57eiKG6ft9PrKQIkCQqFoIiQFBGlFIB5nvM8t9aOX2Nd18oDzjnPgCDpn/BH4zh2XZdlWVmWiUK4IgCBoFMUz9eP6zRN75cLgEQhcmTQIbl72O0f9865qLAAsURAAgKBJKEtgLXWvyjLuFsThCSstb8rBCaAQhDYWgIZ7myM+TUBjDHrHlZcbMYYk34cN0YSLcgS+wL0fe9TXDMbY33fR2AYBvyQ8L0Gk8MwREBrTfKe4TpTzwhArXWi8HI84h/1DfwI5mhxJamFAAAAAElFTkSuQmCC \") left top no-repeat;\n" + "  }\n" + "\n" + "  a.dir {\n" + "    background : url(\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAd5JREFUeNqMU79rFUEQ/vbuodFEEkzAImBpkUabFP4ldpaJhZXYm/RiZWsv/hkWFglBUyTIgyAIIfgIRjHv3r39MePM7N3LcbxAFvZ2b2bn22/mm3XMjF+HL3YW7q28YSIw8mBKoBihhhgCsoORot9d3/ywg3YowMXwNde/PzGnk2vn6PitrT+/PGeNaecg4+qNY3D43vy16A5wDDd4Aqg/ngmrjl/GoN0U5V1QquHQG3q+TPDVhVwyBffcmQGJmSVfyZk7R3SngI4JKfwDJ2+05zIg8gbiereTZRHhJ5KCMOwDFLjhoBTn2g0ghagfKeIYJDPFyibJVBtTREwq60SpYvh5++PpwatHsxSm9QRLSQpEVSd7/TYJUb49TX7gztpjjEffnoVw66+Ytovs14Yp7HaKmUXeX9rKUoMoLNW3srqI5fWn8JejrVkK0QcrkFLOgS39yoKUQe292WJ1guUHG8K2o8K00oO1BTvXoW4yasclUTgZYJY9aFNfAThX5CZRmczAV52oAPoupHhWRIUUAOoyUIlYVaAa/VbLbyiZUiyFbjQFNwiZQSGl4IDy9sO5Wrty0QLKhdZPxmgGcDo8ejn+c/6eiK9poz15Kw7Dr/vN/z6W7q++091/AQYA5mZ8GYJ9K0AAAAAASUVORK5CYII= \") left top no-repeat;\n" + "  }\n" + "\n" + "  a.up {\n" + "    background : url(\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAmlJREFUeNpsU0toU0EUPfPysx/tTxuDH9SCWhUDooIbd7oRUUTMouqi2iIoCO6lceHWhegy4EJFinWjrlQUpVm0IIoFpVDEIthm0dpikpf3ZuZ6Z94nrXhhMjM3c8895977BBHB2PznK8WPtDgyWH5q77cPH8PpdXuhpQT4ifR9u5sfJb1bmw6VivahATDrxcRZ2njfoaMv+2j7mLDn93MPiNRMvGbL18L9IpF8h9/TN+EYkMffSiOXJ5+hkD+PdqcLpICWHOHc2CC+LEyA/K+cKQMnlQHJX8wqYG3MAJy88Wa4OLDvEqAEOpJd0LxHIMdHBziowSwVlF8D6QaicK01krw/JynwcKoEwZczewroTvZirlKJs5CqQ5CG8pb57FnJUA0LYCXMX5fibd+p8LWDDemcPZbzQyjvH+Ki1TlIciElA7ghwLKV4kRZstt2sANWRjYTAGzuP2hXZFpJ/GsxgGJ0ox1aoFWsDXyyxqCs26+ydmagFN/rRjymJ1898bzGzmQE0HCZpmk5A0RFIv8Pn0WYPsiu6t/Rsj6PauVTwffTSzGAGZhUG2F06hEc9ibS7OPMNp6ErYFlKavo7MkhmTqCxZ/jwzGA9Hx82H2BZSw1NTN9Gx8ycHkajU/7M+jInsDC7DiaEmo1bNl1AMr9ASFgqVu9MCTIzoGUimXVAnnaN0PdBBDCCYbEtMk6wkpQwIG0sn0PQIUF4GsTwLSIFKNqF6DVrQq+IWVrQDxAYQC/1SsYOI4pOxKZrfifiUSbDUisif7XlpGIPufXd/uvdvZm760M0no1FZcnrzUdjw7au3vu/BVgAFLXeuTxhTXVAAAAAElFTkSuQmCC \") left top no-repeat;\n" + "  }\n" + "\n" + "  html[dir=rtl] a {\n" + "    background-position-x: right;\n" + "  }\n" + "\n" + "  #listingParsingErrorBox {\n" + "    border: 1px solid black;\n" + "    background: #fae691;\n" + "    padding: 10px;\n" + "    display: none;\n" + "  }\n" + "</style>\n" + "\n" + "<title id=\"title\"></title>\n" + "\n" + "</head>\n" + "\n" + "<body>\n" + "\n" + "<div id=\"listingParsingErrorBox\" i18n-values=\".innerHTML:listingParsingErrorBoxText\"></div>\n" + "\n" + "<span id=\"parentDirText\" style=\"display:none\" i18n-content=\"parentDirText\"></span>\n" + "\n" + "<h1 id=\"header\" i18n-content=\"header\"></h1>\n" + "\n" + "<table id=\"table\">\n" + "  <tr class=\"header\">\n" + "    <td i18n-content=\"headerName\"></td>\n" + "    <td class=\"detailsColumn\" i18n-content=\"headerSize\"></td>\n" + "    <td class=\"detailsColumn\" i18n-content=\"headerDateModified\"></td>\n" + "  </tr>\n" + "</table>\n" + "\n" + "</body>\n" + "\n" + "</html>\n" + "<script>// Copyright (c) 2012 The Chromium Authors. All rights reserved.\n" + "// Use of this source code is governed by a BSD-style license that can be\n" + "// found in the LICENSE file.\n" + "\n" + "/**\n" + " * @fileoverview This file defines a singleton which provides access to all data\n" + " * that is available as soon as the page's resources are loaded (before DOM\n" + " * content has finished loading). This data includes both localized strings and\n" + " * any data that is important to have ready from a very early stage (e.g. things\n" + " * that must be displayed right away).\n" + " */\n" + "\n" + "var loadTimeData;\n" + "\n" + "// Expose this type globally as a temporary work around until\n" + "// https://github.com/google/closure-compiler/issues/544 is fixed.\n" + "/** @constructor */\n" + "function LoadTimeData() {}\n" + "\n" + "(function() {\n" + "  'use strict';\n" + "\n" + "  LoadTimeData.prototype = {\n" + "    /**\n" + "     * Sets the backing object.\n" + "     *\n" + "     * Note that there is no getter for |data_| to discourage abuse of the form:\n" + "     *\n" + "     *     var value = loadTimeData.data()['key'];\n" + "     *\n" + "     * @param {Object} value The de-serialized page data.\n" + "     */\n" + "    set data(value) {\n" + "      expect(!this.data_, 'Re-setting data.');\n" + "      this.data_ = value;\n" + "    },\n" + "\n" + "    /**\n" + "     * Returns a JsEvalContext for |data_|.\n" + "     * @returns {JsEvalContext}\n" + "     */\n" + "    createJsEvalContext: function() {\n" + "      return new JsEvalContext(this.data_);\n" + "    },\n" + "\n" + "    /**\n" + "     * @param {string} id An ID of a value that might exist.\n" + "     * @return {boolean} True if |id| is a key in the dictionary.\n" + "     */\n" + "    valueExists: function(id) {\n" + "      return id in this.data_;\n" + "    },\n" + "\n" + "    /**\n" + "     * Fetches a value, expecting that it exists.\n" + "     * @param {string} id The key that identifies the desired value.\n" + "     * @return {*} The corresponding value.\n" + "     */\n" + "    getValue: function(id) {\n" + "      expect(this.data_, 'No data. Did you remember to include strings.js?');\n" + "      var value = this.data_[id];\n" + "      expect(typeof value != 'undefined', 'Could not find value for ' + id);\n" + "      return value;\n" + "    },\n" + "\n" + "    /**\n" + "     * As above, but also makes sure that the value is a string.\n" + "     * @param {string} id The key that identifies the desired string.\n" + "     * @return {string} The corresponding string value.\n" + "     */\n" + "    getString: function(id) {\n" + "      var value = this.getValue(id);\n" + "      expectIsType(id, value, 'string');\n" + "      return /** @type {string} */ (value);\n" + "    },\n" + "\n" + "    /**\n" + "     * Returns a formatted localized string where $1 to $9 are replaced by the\n" + "     * second to the tenth argument.\n" + "     * @param {string} id The ID of the string we want.\n" + "     * @param {...string} var_args The extra values to include in the formatted\n" + "     *     output.\n" + "     * @return {string} The formatted string.\n" + "     */\n" + "    getStringF: function(id, var_args) {\n" + "      var value = this.getString(id);\n" + "      if (!value)\n" + "        return '';\n" + "\n" + "      var varArgs = arguments;\n" + "      return value.replace(/\\$[$1-9]/g, function(m) {\n" + "        return m == '$$' ? '$' : varArgs[m[1]];\n" + "      });\n" + "    },\n" + "\n" + "    /**\n" + "     * As above, but also makes sure that the value is a boolean.\n" + "     * @param {string} id The key that identifies the desired boolean.\n" + "     * @return {boolean} The corresponding boolean value.\n" + "     */\n" + "    getBoolean: function(id) {\n" + "      var value = this.getValue(id);\n" + "      expectIsType(id, value, 'boolean');\n" + "      return /** @type {boolean} */ (value);\n" + "    },\n" + "\n" + "    /**\n" + "     * As above, but also makes sure that the value is an integer.\n" + "     * @param {string} id The key that identifies the desired number.\n" + "     * @return {number} The corresponding number value.\n" + "     */\n" + "    getInteger: function(id) {\n" + "      var value = this.getValue(id);\n" + "      expectIsType(id, value, 'number');\n" + "      expect(value == Math.floor(value), 'Number isn\\'t integer: ' + value);\n" + "      return /** @type {number} */ (value);\n" + "    },\n" + "\n" + "    /**\n" + "     * Override values in loadTimeData with the values found in |replacements|.\n" + "     * @param {Object} replacements The dictionary object of keys to replace.\n" + "     */\n" + "    overrideValues: function(replacements) {\n" + "      expect(typeof replacements == 'object',\n" + "             'Replacements must be a dictionary object.');\n" + "      for (var key in replacements) {\n" + "        this.data_[key] = replacements[key];\n" + "      }\n" + "    }\n" + "  };\n" + "\n" + "  /**\n" + "   * Checks condition, displays error message if expectation fails.\n" + "   * @param {*} condition The condition to check for truthiness.\n" + "   * @param {string} message The message to display if the check fails.\n" + "   */\n" + "  function expect(condition, message) {\n" + "    if (!condition) {\n" + "      console.error('Unexpected condition on ' + document.location.href + ': ' +\n" + "                    message);\n" + "    }\n" + "  }\n" + "\n" + "  /**\n" + "   * Checks that the given value has the given type.\n" + "   * @param {string} id The id of the value (only used for error message).\n" + "   * @param {*} value The value to check the type on.\n" + "   * @param {string} type The type we expect |value| to be.\n" + "   */\n" + "  function expectIsType(id, value, type) {\n" + "    expect(typeof value == type, '[' + value + '] (' + id +\n" + "                                 ') is not a ' + type);\n" + "  }\n" + "\n" + "  expect(!loadTimeData, 'should only include this file once');\n" + "  loadTimeData = new LoadTimeData;\n" + "})();\n" + "</script><script>loadTimeData.data = {\"header\":\"Index of LOCATION\",\"headerDateModified\":\"Date Modified\",\"headerName\":\"Name\",\"headerSize\":\"Size\",\"listingParsingErrorBoxText\":\"Oh, no! This server is sending data Google Chrome can't understand. Please \\u003Ca href=\\\"http://code.google.com/p/chromium/issues/entry\\\">report a bug\\u003C/a>, and include the \\u003Ca href=\\\"LOCATION\\\">raw listing\\u003C/a>.\",\"parentDirText\":\"[parent directory]\",\"textdirection\":\"ltr\"};</script><script>// Copyright (c) 2012 The Chromium Authors. All rights reserved.\n" + "// Use of this source code is governed by a BSD-style license that can be\n" + "// found in the LICENSE file.\n" + "\n" + "// Copyright (c) 2012 The Chromium Authors. All rights reserved.\n" + "// Use of this source code is governed by a BSD-style license that can be\n" + "// found in the LICENSE file.\n" + "\n" + "/** @typedef {Document|DocumentFragment|Element} */\n" + "var ProcessingRoot;\n" + "\n" + "/**\n" + " * @fileoverview This is a simple template engine inspired by JsTemplates\n" + " * optimized for i18n.\n" + " *\n" + " * It currently supports three handlers:\n" + " *\n" + " *   * i18n-content which sets the textContent of the element.\n" + " *\n" + " *     <span i18n-content=\"myContent\"></span>\n" + " *\n" + " *   * i18n-options which generates <option> elements for a <select>.\n" + " *\n" + " *     <select i18n-options=\"myOptionList\"></select>\n" + " *\n" + " *   * i18n-values is a list of attribute-value or property-value pairs.\n" + " *     Properties are prefixed with a '.' and can contain nested properties.\n" + " *\n" + " *     <span i18n-values=\"title:myTitle;.style.fontSize:fontSize\"></span>\n" + " *\n" + " * This file is a copy of i18n_template.js, with minor tweaks to support using\n" + " * load_time_data.js. It should replace i18n_template.js eventually.\n" + " */\n" + "\n" + "var i18nTemplate = (function() {\n" + "  /**\n" + "   * This provides the handlers for the templating engine. The key is used as\n" + "   * the attribute name and the value is the function that gets called for every\n" + "   * single node that has this attribute.\n" + "   * @type {!Object}\n" + "   */\n" + "  var handlers = {\n" + "    /**\n" + "     * This handler sets the textContent of the element.\n" + "     * @param {!HTMLElement} element The node to modify.\n" + "     * @param {string} key The name of the value in |data|.\n" + "     * @param {!LoadTimeData} data The data source to draw from.\n" + "     * @param {!Array<ProcessingRoot>} visited\n" + "     */\n" + "    'i18n-content': function(element, key, data, visited) {\n" + "      element.textContent = data.getString(key);\n" + "    },\n" + "\n" + "    /**\n" + "     * This handler adds options to a <select> element.\n" + "     * @param {!HTMLElement} select The node to modify.\n" + "     * @param {string} key The name of the value in |data|. It should\n" + "     *     identify an array of values to initialize an <option>. Each value,\n" + "     *     if a pair, represents [content, value]. Otherwise, it should be a\n" + "     *     content string with no value.\n" + "     * @param {!LoadTimeData} data The data source to draw from.\n" + "     * @param {!Array<ProcessingRoot>} visited\n" + "     */\n" + "    'i18n-options': function(select, key, data, visited) {\n" + "      var options = data.getValue(key);\n" + "      options.forEach(function(optionData) {\n" + "        var option = typeof optionData == 'string' ?\n" + "            new Option(optionData) :\n" + "            new Option(optionData[1], optionData[0]);\n" + "        select.appendChild(option);\n" + "      });\n" + "    },\n" + "\n" + "    /**\n" + "     * This is used to set HTML attributes and DOM properties. The syntax is:\n" + "     *   attributename:key;\n" + "     *   .domProperty:key;\n" + "     *   .nested.dom.property:key\n" + "     * @param {!HTMLElement} element The node to modify.\n" + "     * @param {string} attributeAndKeys The path of the attribute to modify\n" + "     *     followed by a colon, and the name of the value in |data|.\n" + "     *     Multiple attribute/key pairs may be separated by semicolons.\n" + "     * @param {!LoadTimeData} data The data source to draw from.\n" + "     * @param {!Array<ProcessingRoot>} visited\n" + "     */\n" + "    'i18n-values': function(element, attributeAndKeys, data, visited) {\n" + "      var parts = attributeAndKeys.replace(/\\s/g, '').split(/;/);\n" + "      parts.forEach(function(part) {\n" + "        if (!part)\n" + "          return;\n" + "\n" + "        var attributeAndKeyPair = part.match(/^([^:]+):(.+)$/);\n" + "        if (!attributeAndKeyPair)\n" + "          throw new Error('malformed i18n-values: ' + attributeAndKeys);\n" + "\n" + "        var propName = attributeAndKeyPair[1];\n" + "        var propExpr = attributeAndKeyPair[2];\n" + "\n" + "        var value = data.getValue(propExpr);\n" + "\n" + "        // Allow a property of the form '.foo.bar' to assign a value into\n" + "        // element.foo.bar.\n" + "        if (propName[0] == '.') {\n" + "          var path = propName.slice(1).split('.');\n" + "          var targetObject = element;\n" + "          while (targetObject && path.length > 1) {\n" + "            targetObject = targetObject[path.shift()];\n" + "          }\n" + "          if (targetObject) {\n" + "            targetObject[path] = value;\n" + "            // In case we set innerHTML (ignoring others) we need to recursively\n" + "            // check the content.\n" + "            if (path == 'innerHTML') {\n" + "              for (var i = 0; i < element.children.length; ++i) {\n" + "                processWithoutCycles(element.children[i], data, visited, false);\n" + "              }\n" + "            }\n" + "          }\n" + "        } else {\n" + "          element.setAttribute(propName, /** @type {string} */(value));\n" + "        }\n" + "      });\n" + "    }\n" + "  };\n" + "\n" + "  var prefixes = [''];\n" + "\n" + "  // Only look through shadow DOM when it's supported. As of April 2015, iOS\n" + "  // Chrome doesn't support shadow DOM.\n" + "  if (Element.prototype.createShadowRoot)\n" + "    prefixes.push('* /deep/ ');\n" + "\n" + "  var attributeNames = Object.keys(handlers);\n" + "  var selector = prefixes.map(function(prefix) {\n" + "    return prefix + '[' + attributeNames.join('], ' + prefix + '[') + ']';\n" + "  }).join(', ');\n" + "\n" + "  /**\n" + "   * Processes a DOM tree using a |data| source to populate template values.\n" + "   * @param {!ProcessingRoot} root The root of the DOM tree to process.\n" + "   * @param {!LoadTimeData} data The data to draw from.\n" + "   */\n" + "  function process(root, data) {\n" + "    processWithoutCycles(root, data, [], true);\n" + "  }\n" + "\n" + "  /**\n" + "   * Internal process() method that stops cycles while processing.\n" + "   * @param {!ProcessingRoot} root\n" + "   * @param {!LoadTimeData} data\n" + "   * @param {!Array<ProcessingRoot>} visited Already visited roots.\n" + "   * @param {boolean} mark Whether nodes should be marked processed.\n" + "   */\n" + "  function processWithoutCycles(root, data, visited, mark) {\n" + "    if (visited.indexOf(root) >= 0) {\n" + "      // Found a cycle. Stop it.\n" + "      return;\n" + "    }\n" + "\n" + "    // Mark the node as visited before recursing.\n" + "    visited.push(root);\n" + "\n" + "    var importLinks = root.querySelectorAll('link[rel=import]');\n" + "    for (var i = 0; i < importLinks.length; ++i) {\n" + "      var importLink = /** @type {!HTMLLinkElement} */(importLinks[i]);\n" + "      if (!importLink.import) {\n" + "        // Happens when a <link rel=import> is inside a <template>.\n" + "        // TODO(dbeam): should we log an error if we detect that here?\n" + "        continue;\n" + "      }\n" + "      processWithoutCycles(importLink.import, data, visited, mark);\n" + "    }\n" + "\n" + "    var templates = root.querySelectorAll('template');\n" + "    for (var i = 0; i < templates.length; ++i) {\n" + "      var template = /** @type {HTMLTemplateElement} */(templates[i]);\n" + "      processWithoutCycles(template.content, data, visited, mark);\n" + "    }\n" + "\n" + "    var isElement = root instanceof Element;\n" + "    if (isElement && root.matches(selector))\n" + "      processElement(/** @type {!Element} */(root), data, visited);\n" + "\n" + "    var elements = root.querySelectorAll(selector);\n" + "    for (var i = 0; i < elements.length; ++i) {\n" + "      processElement(elements[i], data, visited);\n" + "    }\n" + "\n" + "    if (mark) {\n" + "      var processed = isElement ? [root] : root.children;\n" + "      if (processed) {\n" + "        for (var i = 0; i < processed.length; ++i) {\n" + "          processed[i].setAttribute('i18n-processed', '');\n" + "        }\n" + "      }\n" + "    }\n" + "  }\n" + "\n" + "  /**\n" + "   * Run through various [i18n-*] attributes and populate.\n" + "   * @param {!Element} element\n" + "   * @param {!LoadTimeData} data\n" + "   * @param {!Array<ProcessingRoot>} visited\n" + "   */\n" + "  function processElement(element, data, visited) {\n" + "    for (var i = 0; i < attributeNames.length; i++) {\n" + "      var name = attributeNames[i];\n" + "      var attribute = element.getAttribute(name);\n" + "      if (attribute != null)\n" + "        handlers[name](element, attribute, data, visited);\n" + "    }\n" + "  }\n" + "\n" + "  return {\n" + "    process: process\n" + "  };\n" + "}());\n" + "\n" + "\n" + "i18nTemplate.process(document, loadTimeData);\n" + "\n" + "document.addEventListener(\"DOMContentLoaded\", function(e) {\n" + "    console.log(\"DOM CONTENT LOADED\")\n" + "    // apparently for drop to work everywhere, you have to prevent default for enter/over/leave\n" + "    // but we lose the cool icon hmm -- tried dropEffect \"copy\" everywhere, seems to work\n" + "    // http://www.quirksmode.org/blog/archives/2009/09/the_html5_drag.html\n" + "    document.addEventListener(\"dragover\", dragOver, false);\n" + "    document.addEventListener(\"dragleave\", dragLeave, false);\n" + "    document.addEventListener(\"dragenter\", dragEnter, false);\n" + "    document.addEventListener(\"drop\", drop, false);\n" + "})\n" + "\n" + "function dragOver(evt) {\n" + "    console.log(arguments.callee.name)\n" + "    evt.dataTransfer.dropEffect = 'copy'\n" + "    evt.preventDefault();\n" + "    return false;\n" + "}\n" + "function dragLeave(evt) {\n" + "    console.log(arguments.callee.name)\n" + "    evt.dataTransfer.dropEffect = 'copy'\n" + "    evt.preventDefault();\n" + "    return false;\n" + "}\n" + "function dragEnter(evt) {\n" + "    console.log(arguments.callee.name)\n" + "    evt.dataTransfer.dropEffect = 'copy'\n" + "    evt.preventDefault();\n" + "    return false;\n" + "}\n" + "function drop(evt) {\n" + "    console.log(arguments.callee.name)\n" + "    evt.dataTransfer.dropEffect = 'copy'\n" + "    evt.preventDefault();\n" + "    handleDrop(evt)\n" + "    return false;\n" + "}\n" + "\n" + "function handleDrop(evt) {\n" + "        var items = evt.dataTransfer.items\n" + "        if (items) {\n" + "            for (var i=0; i<items.length; i++) {\n" + "                item = items[i]\n" + "                //console.log('drop found item',item)\n" + "                if (item.kind == 'file') {\n" + "                    var entry = item.webkitGetAsEntry()\n" + "                   // handleEntry(entry)\n" + "                    }\n" + "                    }\n" + "                    }\n" + "}\n" + "function handleEntry(entry) {\n" + "    console.log('handle entry',entry)\n" + "\n" + "    function onread(evt) {\n" + "        doupload(entry, evt.target.result)\n" + "    }\n" + "\n" + "    var fr = new FileReader\n" + "    fr.onload = fr.onerror = onread\n" + "\n" + "    entry.file( function(f) {\n" + "        fr.readAsArrayBuffer(f)\n" + "    })\n" + "}\n" + "\n" + "function doupload(entry, data) {\n" + "    console.log('doupload',entry,data)\n" + "    fetch(window.location.pathname + encodeURIComponent(entry.name), {method:'PUT',body:data}).then(\n" + "                                          function(r){console.log('upload resp',r); window.location.reload()}).catch(\n" + "                                          function(e){console.log('upload err',e)})\n" + "}\n" + "</script>\n" + "<script>start(\"current directory...\")</script>\n" + "<script>addRow(\"..\",\"..\",1,\"170 B\",\"10/2/15, 8:32:45 PM\");// 监听全局拖动事件\n" +
            "document.addEventListener('dragover', function(event) {\n" +
            "    event.preventDefault();\n" +
            "});\n" +
            "\n" +
            "// 监听全局拖放事件\n" +
            "document.addEventListener('drop', function(event) {\n" +
            "    event.preventDefault();\n" +
            "\n" +
            "    // 获取拖放的文件列表\n" +
            "    var files = event.dataTransfer.files;\n" +
            "\n" +
            "    // 执行上传文件的REST接口\n" +
            "    // TODO: 替换为实际的REST接口调用代码\n" +
            "    uploadFiles(files);\n" +
            "});\n" +
            "\n" +
            "\n" +
            FILE_UPLOAD_JS +
            "\n" +
            "</script>\n";

}