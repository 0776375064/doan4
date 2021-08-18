<%-- 
    Document   : modaOrderUser
    Created on : Jul 28, 2021, 2:02:52 AM
    Author     : hmtua
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">  
<style>
    .form-group {
        margin-bottom: 1rem;
    }
    .card {
        position: relative;
        display: -webkit-box;
        display: flex;
        -webkit-box-orient: vertical;
        -webkit-box-direction: normal;
        flex-direction: column;
        min-width: 0;
        word-wrap: break-word;
        background-color: #fff;
        background-clip: border-box;
        border: 1px solid rgba(0,0,0,.125);
        border-radius: .25rem;
    }
    .card-header {
        padding: .75rem 1.25rem;
        margin-bottom: 0;
        background-color: rgba(0,0,0,.03);
        border-bottom: 1px solid rgba(0,0,0,.125);
    }
    .card-body {
        -webkit-box-flex: 1;
        flex: 1 1 auto;
        padding: 1.25rem;
    }
</style>
<script>
    <%
        if ( request.getParameter("trangThai")!= null) {
    %>
    <%if (request.getParameter("trangThai").equals("0")) {%>
    Swal.fire(
            'Successful Transaction',
            'Please continue to enjoy the music!',
            'success'
            );
    <%} else {%>
    Swal.fire(
            'Payment Error',
            'Please re-create it, Or contact the administrator for help',
            'error'
            );
    <%}
        }%>

    function loatOrder(idSong) {
        var x = location.href;
        $.ajax({
            url: $("#url").val() + "/Order?setSession=host",
            dataType: 'text',
            type: 'get',
            cache: false,
            data: {
                idSong: idSong,
                urlHost: x,
            },
            success: function (result) {
                $("#dataorder").html(result);
                //console.log(result);
            },

            error: function () {
                document.getElementById('id01').style.display = 'none';
                Swal.fire(
                        'You must login before you can buy this song',
                        'Please log in! 🤗🤗🤗🤗🤗🤗',
                        'error'
                        );
            }
        });
    }

    //tao trang thanh toan nap tien 
    function loatAddMony() {
        var Mony = document.getElementById("mony").value;
        var x = location.href;
        $.ajax({
            url: $("#url").val() + "/Order?addMony=add",
            dataType: 'text',
            type: 'get',
            cache: false,
            data: {
                mony: Mony,
                urlHost: x,
            },
            success: function (result) {
                $("#dataorder").html(result);
                //console.log(result);
            },
            error: function (e) {
                document.getElementById('id01').style.display = 'none';
                Swal.fire(
                        'Error',
                        e,
                        'error'
                        );
            }
        });
    }
</script>

<div id="id01" class="w3-modal" style="border-radius:4px">
    <div class="w3-modal-content w3-animate-top w3-card-4" style="width:70%;border-radius:4px">
        <header class="w3-container "> 
            <span onclick="document.getElementById('id01').style.display = 'none'" 
                  class="w3-button w3-display-topright">&times;</span>
        </header>
        <div class="w3-container">
            <div id="dataorder"> </div>
        </div>
        <footer class="w3-container">
        </footer>
    </div>
</div>
<!-- nap tien -->
<div id="addMony" class="w3-modal" style="border-radius:4px">
    <div class="w3-modal-content w3-animate-top w3-card-4" style="width:30%;border-radius:4px">
        <br/>
        <header class="w3-container "> 
            <span onclick="document.getElementById('addMony').style.display = 'none'" 
                  class="w3-button w3-display-topright">&times;</span>
        </header>
        <div style="color:black">
            <div class="w3-container">
                <center>
                    <img height="100px" src="https://img.icons8.com/color-glass/48/000000/deposit.png"/>
                </center>
                <div >
                    <p>Recharge from users: ${account.fullname}</p>
                    <p>Account: ${account.username}</p>
                </div>
                <div>
                    <p>
                    <style>
                        #mony{
                            width:70%;
                        }
                    </style>
                    <label>
                        Amount to deposit : 
                        <input type="number" id="mony"  placeholder="Enter the amount to deposit" class="w3-input w3-border w3-round-larg">
                    </label>
                    </p>
                </div>
            </div>
        </div>
        <br/>
        <footer class="w3-container">
            <div style="text-align:right">
                <label >
                    <div style="text-align:right">
                        <button  class="w3-button w3-blue w3-round-xxlarge"  onclick="document.getElementById('addMony').style.display = 'none';loatAddMony();document.getElementById('id01').style.display = 'block'" >
                            <img style=";content:\f09d "height="20px" src="https://img.icons8.com/pastel-glyph/24/000000/heck-for-payment--v2.png"/> Payment
                        </button>
                        <button  type="button" onclick="document.getElementById('addMony').style.display = 'none'"  class="w3-button w3-red w3-round-xxlarge">Close</button>       
                    </div>
                </label>                                         
            </div>
            <br/>
        </footer>
    </div>
</div>

<!---thanh toan thong qua Bcoin-->
<div id="Bcoin" class="w3-modal" style="border-radius:4px">
    <div class="w3-modal-content w3-animate-top w3-card-4" style="width:380px;height:320px;border-radius:4px">
        <header class="w3-container "> 
        </header>
        <div class="w3-container">
            <div> 
                <br/>
                <center>
                    <img width="125px" src="https://img.icons8.com/bubbles/100/000000/test-account.png"/>
                </center>
                <br/>
                <div style="color: black">
                    <label>Please re-enter your password</label>
                    <input class="w3-input w3-border w3-round-xxlarge" name="last" type="password"  id="pass" placeholder="Enter PassWord">
                    <p style="color: red" id="loiDN"></p>
                    <br/>
                </div>
            </div>
        </div>
        <footer class="w3-container">
            <div style="text-align:center">
                <button  class="w3-button w3-blue w3-round-xxlarge"  onclick="document.getElementById('addMony').style.display = 'none';ConfirmThanhToan()" >
                    Confirm
                </button>
                <button  type="button" onclick="document.getElementById('Bcoin').style.display = 'none'"  class="w3-button w3-red w3-round-xxlarge">Close</button>       
            </div>
        </footer>
    </div>
    <script>
        function ConfirmThanhToan() {
            var pass = document.getElementById('pass').value;
            if (pass == "") {
                document.getElementById('loiDN').innerHTML = 'Please enter password word';
            } else {
                $.ajax({
                    url: $("#url").val() + "/Moda?getPass",
                    dataType: 'text',
                    type: 'post',
                    cache: false,
                    data: {
                        pass: pass
                    },
                    success: function (result) {
                        $("#loiDN").html(result);
                    },
                    error: function (e) {
                        $("#loiDN").html(e);
                    }
                });
            }
        }
    </script>
</div>