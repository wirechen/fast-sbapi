<html>
<head>
    <meta charset="utf-8">
    <title>FastSbAPI代码生成器</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/3.3.0/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <style>
        .myselect {
            width: 220px;
            height: 30px;
            border: 1px solid #ccc;
            background: transparent;
            padding: 5px;
        }
        .checkbox { position: relative; height: 30px; }
        .checkbox input[type='checkbox'] { position: absolute; left: 0; top: 0; width: 20px; height: 20px; opacity: 0; }
        .checkbox label { position: absolute; left: 30px; top: 0; height: 20px; line-height: 20px; }
        .checkbox label:before { content: ''; position: absolute; left: -30px; top: 0; width: 20px; height: 20px; border: 1px solid #ddd; border-radius: 50%; transition: all 0.3s ease; -webkit-transition: all 0.3s ease; -moz-transition: all 0.3s ease; }
        .checkbox label:after { content: ''; position: absolute; left: -22px; top: 3px; width: 6px; height: 12px; border: 0; border-right: 1px solid #fff; border-bottom: 1px solid #fff; background: #fff; transform: rotate(45deg); -webkit-transform: rotate(45deg); -moz-transform: rotate(45deg); -ms-transform: rotate(45deg); transition: all 0.3s ease; -webkit-transition: all 0.3s ease; -moz-transition: all 0.3s ease; }
        .checkbox input[type='checkbox']:checked + label:before { background: #7db050; border-color: #7db050; } .checkbox input[type='checkbox']:checked + label:after { background: #7db050; }

    </style>

</head>
<body>
<div class="container" style="margin-top: 70px">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h2 class="text-center" style="margin-bottom: 20px; font-style:oblique; color:#7db050;">
                <strong>FastSbAPI</strong>
            </h2>
            <table class="table table-bordered"">
                <thead>
                <tr>
                    <th colspan="2">
                        <span style="color:#7db050;">ModelName:</span>
                        <input type="text"  id="model_name" placeholder="请遵循大驼峰命名法"/>
                    </th>
                    <th colspan="3">
                        <span style="color:#7db050;">TableName:</span>
                        <input type="text" id="table_name" placeholder="自定义表名,可不填"/>
                    </th>
                </tr>
                </thead>
                <thead>
                <tr>
                    <th>
                        字段
                    </th>
                    <th>
                        类型
                    </th>
                    <th>
                        允许为空
                    </th>
                    <th>
                        备注
                    </th>
                    <th>

                    </th>
                </tr>
                </thead>
                <tbody>
                <tr class="fields">
                    <td>
                        <input type="text" class="form-control" placeholder="请遵循小驼峰命名法"/>
                    </td>
                    <td>
                        <select class="myselect">
                            <option value ="String">String</option>
                            <option value ="Integer">Integer</option>
                            <option value ="Long">Long</option>
                            <option value="Boolean">Boolean</option>
                            <option value="Date">Date</option>
                            <option value="BigDecimal">BigDecimal</option>
                        </select>
                    </td>
                    <td>
                        <select class="myselect">
                            <option value ="1">是</option>
                            <option value ="0">否</option>
                        </select>
                    </td>
                    <td>
                        <input type="text" class="form-control" id="mark" placeholder="没有可不填"/>
                    </td>
                    <td>
                        <span class="glyphicon glyphicon-remove-circle" onclick="removeField(this);" style="padding-top:10px; cursor: pointer;"></span>
                    </td>
                </tr>
                <tr class="add_field">
                    <td colspan="5">
                        <button id="add" type="button" class="btn btn-default btn-block btn-default">添加字段</button>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class='checkbox'>
                <input type='checkbox' id='checkbox1' name='checkboox[]' value="">
                <label for='checkbox1'>是否需要SQL脚本</label>
            </div>
            <button id="create" type="button" class="btn btn-info btn-default">生成代码</button>

            <div class="modal fade" id="modal-container" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title" id="myModalLabel">
                                注意
                            </h4>
                        </div>
                        <div class="modal-body">
                            ModelName或字段不能有空
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="modal-container2" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title" id="myModalLabel">
                                成功
                            </h4>
                        </div>
                        <div class="modal-body">
                            代码生成完毕!
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary" onclick="location='${base_path}/code/generate'">继续添加</button>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    $(function(){

        removeField = function (td) {
            td.parentNode.parentNode.remove();
        };

        $('#add').click(function () {
            var field = '<tr class="fields">\n' +
                    '                    <td>\n' +
                    '                        <input type="text" class="form-control" placeholder="请遵循小驼峰命名法"/>\n' +
                    '                    </td>\n' +
                    '                    <td>\n' +
                    '                        <select class="myselect">\n' +
                    '                            <option value ="String">String</option>\n' +
                    '                            <option value ="Integer">Integer</option>\n' +
                    '                            <option value ="Long">Long</option>\n' +
                    '                            <option value="Boolean">Boolean</option>\n' +
                    '                            <option value="Date">Date</option>\n' +
                    '                            <option value="BigDecimal">BigDecimal</option>\n' +
                    '                        </select>\n' +
                    '                    </td>\n' +
                    '                    <td>\n' +
                    '                        <select class="myselect">\n' +
                    '                            <option value ="1">是</option>\n' +
                    '                            <option value ="0">否</option>\n' +
                    '                        </select>\n' +
                    '                    </td>\n' +
                    '                    <td>\n' +
                    '                        <input type="text" class="form-control" id="mark" placeholder="没有可不填"/>\n' +
                    '                    </td>\n' +
                    '                    <td>\n' +
                    '                        <span class="glyphicon glyphicon-remove-circle" style="padding-top:10px; cursor: pointer;" onclick="removeField(this)"></span>\n' +
                    '                    </td>\n' +
                    '                </tr>';

            $(".add_field").before(field);
        });

        $('#create').click(function () {
            var trs = $('tbody').children('.fields');
            var paramObj = {};
            var key_null = 0;
            paramObj.model_name_ = $('#model_name').val().replace(/\s+/g,"");
            paramObj.table_name_ = $('#table_name').val().replace(/\s+/g,"");
            if (paramObj.model_name_ == '') {
                $("#modal-container").modal();
                return false;
            }
            trs.each(function (index,tr) {
                var arry_fields = [];
                $(tr).children('td').each(function (index2,td) {
                    var text = $(td).children(':first').val();
                    if (index2 != 4) {
                        arry_fields.push(text)
                    }
                });
                var key = arry_fields[0];
                if (key.replace(/\s+/g,"") == '') {
                    key_null = 1;
                    return false;
                }
                arry_fields.splice(0, 1);
                paramObj[key] = arry_fields;
            });
            if (key_null == 1) {
                $("#modal-container").modal();
                return false;
            }
            console.log('paramObj', paramObj);
            var checked = $('#checkbox1').prop("checked");
            paramObj.require_sql_ = checked;
            $.ajax({
                async: false,
                url: '${base_path}/code/generate',
                type: 'POST',
                dataType: "json",
                contentType: 'application/json',
                data: JSON.stringify(paramObj),
                success: function (result) {
                    console.log('success', result);
                    if (result.code == 0) {
                        //下载sql
                        var sql_name = result.data.sql_name;
                        console.log('sql_name', sql_name);
                        if (sql_name != '') {
                            window.open('${base_path}/code/download_sql/'+sql_name);
                        }
                        $("#modal-container2").modal();

                    }else {
                        alert('代码生成过程出错!');
                    }
                }
            });
        });


    });
</script>

