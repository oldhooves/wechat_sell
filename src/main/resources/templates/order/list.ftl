<html>

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->

<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list orderDtoPage.content as orderDto>
                        <tr>
                            <td>${orderDto.orderId}</td>
                            <td>${orderDto.buyerName}</td>
                            <td>${orderDto.buyerPhone}</td>
                            <td>${orderDto.buyerAddress}</td>
                            <td>${orderDto.orderAmount}</td>
                            <td>${orderDto.getOrderStatusEnum()}</td>
                            <td>${orderDto.getPayStatusEnum()}</td>
                            <td>${orderDto.createTime}</td>
                            <td><a href="/sell/seller/order/detail?orderId=${orderDto.orderId}">详情</a></td>
                            <td>
                                    <a href="/sell/seller/order/cancel?orderId=${orderDto.orderId}">取消</a>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>

</div>


</body>
</html>