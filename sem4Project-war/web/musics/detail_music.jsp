
<%@page import="hhd.Controller"%>
<%@page import="entities.Artists"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="entities.Songs"%>
<%@page import="entities.Songs_"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script>
    function openCity(evt, cityName) {
        var i, x, tablinks;
        x = document.getElementsByClassName("city");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < x.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" w3-red", "");
        }
        document.getElementById(cityName).style.display = "block";
        evt.currentTarget.className += " w3-red";

    }
    function openCity1(evt, cityName1) {
        var i, x, tablinks;
        x = document.getElementsByClassName("city1");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablink1");
        for (i = 0; i < x.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" w3-red rr", "");
        }
        document.getElementById(cityName1).style.display = "block";
        evt.currentTarget.className += " w3-red rr";

    }
    document.getElementById("an").style.display = "none";
    function hien() {
        document.getElementById("loiBaiHat").style.height = "1500px";
        document.getElementById("an").style.display = "block";
        document.getElementById("hien").style.display = "none";
    }
    function an() {
        document.getElementById("loiBaiHat").style.height = "500px";
        document.getElementById("hien").style.display = "block";
        document.getElementById("an").style.display = "none";
    }

</script>
<script>
    var request;
    window.onload = function ()
    {
        $.ajax({
            url: '${pageContext.request.contextPath}/SongDetail',
            type: 'GET',
            dataType: 'html',
            data: {
                addAlbum: 'all'
            }, success: function (data) {
                var d = document.getElementById("Paris");
                d.innerHTML = data;
            }, error: function (e) {
                alert(e);
            }
        })
    };

    function add(i) {
        $.ajax({
            url: '${pageContext.request.contextPath}/SongDetail',
            type: 'GET',
            dataType: 'html',
            data: {
                addAlbum: i
            }, success: function (data) {
                var d = document.getElementById("Paris");
                d.innerHTML = data;
                Swal.fire(
                        'Add Success!',
                        'You have added a post to favorites🤗🤗🤗!',
                        'success'
                        );
            }, error: function (e) {
                Swal.fire(
                        'More Failure',
                        'Add problem ! Error code :' + e,
                        'error'
                        );
            }
        })
    }
</script>
<style>
    ::-webkit-scrollbar {
        width: 5px;
    }
    ::-webkit-scrollbar-track {
        background: #f1f1f1; 
    }
    ::-webkit-scrollbar-thumb {
        background: #888;
        border-radius:10px;
    }
    ::-webkit-scrollbar-thumb:hover {
        background: #555; 
    }

    .chiTietAnhMusic{
        border-radius: 20px;
    }
    .chiTietAnhCS{
        margin-left:45%;
    }
    .anhCS{
        margin-left:80%;top:100%;
        padding-top:-100px;
    }
    .chiTietAnhCS{
        width:280px;
        border-radius:100%;
        margin-top: 15px
    }
    .row::after {
        content: "";
        clear: both;
        display: table;
    }
    .play{
        border-radius:25px;
        padding:7px 30px;
    }
    .btn-play{
        border-radius:25px;
        padding:7px 30px;
    }
    .btn-order{
        border-radius:25px;
        padding:7px 30px;
    }
    .play:hover{
        background-color: #888;

    }
    .price{
        background-color:#e07334;
        border-radius:25px;
        padding:7px 30px;
    }
    .ndPlay:hover{
        color:#ffffff;
    }
    .thongTin {
        overflow: hidden;
        text-overflow: ellipsis;
        width: 100%;
        height:70px
    }
    .thongTin:hover { 
        transition: 0.8s;
        overflow: visible;
        white-space: pre-line;
        width:100%;
        height:100px;
        overflow-y:scroll; 
    }
    .plays{
        padding-top:15px;
    }
    .w3-container{
        margin-bottom:30px
    }
    .music{
        margin-top:10px
    }
    .gioiThieu{
        background-color:#888
    }
    .thuocTinh:hover{
        font-size:100%;
    }
    .bodyMusic{
        margin-top:20px
    }
    div[name="dsMusicNoiBat"]{
        overflow-y: hidden;
        height:250px;
    }
    div[name="dsMusicNoiBat"]:hover{
        overflow-y:scroll;
        height:250px;
    }
    .loiBaiHat{
        overflow: visible;
        white-space: pre-line;

    }
    .w3-bar{
        border-radius:25px;
        background-color:#888;
        width:77%;
        height:40px

    }
    button[name="chiMuc"]{
        border-radius:25px;
        width:100px;
        height:40px;

    }
    div[name="week-chart"]{
        height:1000px;
        overflow-y:hidden; 
    }

    div[name="week-chart"]:hover{
        height:1000px;
        overflow-y:scroll; 
    }
    .thanhCuon{
        overflow-y:hidden; 
        height:730px;
    }
    .thanhCuon:hover{
        height: 730px;
        overflow-y:scroll; 
    }
    .dsRoiY{
        overflow-y:hidden;
        height:500px;
    }
    .dsRoiY:hover{
        overflow-y:scroll; 
    }

    .keDoc{
        padding-left:10px;
        border-left: 2px solid #cccccc;
    }
    .anHien{
        border:0;
        background:none;
        color:#ffffff
    }
    .itemsMusic:hover{
        background-color:#636768;
        box-shadow: 0px 0px 5px;
    }
    td{
        border: none;
    }
    table{
        border: 0px ;
        border-collapse: collapse;

    }
    .chart-singer:hover{
        color:#f73943;
    }
    .loiBaiHat{
        overflow-y:hidden;
        text-align: center;
    }
    #playList{
        height:730px;
        overflow-y:hidden;
    }
    #playList:hover{
        height:730px;
        overflow-y: scroll;
    }
    .chart-title:hover{
        color: red;
    }
</style>
<div class="section-chart">
    <div class="bg-blur"></div>
    <div class="bg-alpha"></div>
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-12 col-sm-12">
                <div class="week-chart">
                    <div class="w3-row">    
                        <div class="w3-col  w3-container" style="width:3%">

                        </div>
                        <div class="w3-col  w3-container" style="width:60%">
                            <div class="tieuDe">
                                <%                                    Artists artist = new Artists();
                                    artist = (Artists) request.getAttribute("artist");
                                %>
                                <h1><% out.print(artist.getFullname()); %> (<% out.print(artist.getNickname()); %>)</h1>
                                <p class="thongTin">
                                    <% out.print(artist.getDescription()); %> 
                                </p>
                            </div>
                            <div class="plays">
                                <%                    //lấy fais trị id song khac da mua 
                                    String idSongOrder = null;
                                    if (session.getAttribute("accountID") != null) {
                                        idSongOrder = String.valueOf(request.getAttribute("idSongOrder"));
                                    } else {
                                        idSongOrder = null;
                                    }
                                    boolean dow = false;
                                    Songs song = (Songs) request.getAttribute("songDetails");
                                    if (idSongOrder.indexOf(song.getSongID().toString()) != -1 || song.getPrice().toString().equals("0")) {
                                        dow = true;
                                %>
                                <button type="button" data-id="<% out.print(song.getSongID()); %>" class="btn-play">
                                    <b class="ndPlay"><i class='bx bx-play'></i>Play Music</b>
                                </button>
                                <%
                                } else {
                                    if (song.getPrice() != null || song.getPrice() != 0) {
                                        dow = false;
                                %>
                                <button type="button" data-src="<% out.print(song.getPath()); %>" class="btn-order">
                                    <a onclick="document.getElementById('id01').style.display = 'block';
                                            loatOrder(<% out.print(song.getSongID()); %>)"  href="#"  class="price">
                                        <b class="">Buy <% out.print(song.getPrice()); %>$</b>    
                                    </a> 
                                </button>

                                <%
                                } else {
                                    dow = true;
                                    //duong dan nam o <% out.print(song.getPath());
                                %>
                                <button  type="button" data-id="<% out.print(song.getSongID()); %>" class="btn-play">
                                    <b class="ndPlay"><i class='bx bx-play'></i>Play Music</b>
                                </button>
                                <%
                                        }

                                    }
                                %>
                            </div>
                            <div class="music">
                                <%
                                    String soceImg = song.getThumbnail();
                                    if (soceImg == null) {
                                        soceImg = "https://img.icons8.com/cute-clipart/64/000000/test-account.png";
                                    }
                                %>
                                <div class="w3-col  w3-container" style="width:12%">
                                    <img class="chiTietAnhMusic" src="${pageContext.request.contextPath}/storage/song/${songDetails.thumbnail}" alt="">
                                </div>
                                <div class="w3-col  w3-container" style="width:80%">
                                    <b class="tieuDeMusic">${songDetails.songName}</b>

                                    <p class="date" style="font-size:12px;color: #cccccc">
                                        <%
                                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                            String strDate = formatter.format(song.getRelease());
                                            out.print(strDate);
                                        %>
                                    </p>
                                    <style>
                                        div[name="add"]{
                                            width:5%;
                                        }  
                                    </style>

                                    <div class="w3-col  add"  name="add" >
                                        <span id="ketqua"> </span>
                                        <a href="#" onclick="add(<%  out.print(song.getSongID()); %>)">
                                            <p class="w3-tooltip">
                                                <img height="20px" src="${pageContext.request.contextPath}/storage/album/like.png" alt=""/>
                                                <span style="position:absolute;left:0;bottom:18px; border-radius:10px;width:200px" class="w3-text w3-blue w3-tag ">
                                                    Add to favorite album
                                                </span> 
                                            </p>
                                        </a>
                                    </div>
                                    <div class="w3-col  dow" name="add">
                                        <%
                                            if (dow) {
                                        %>
                                        <a href="${pageContext.request.contextPath}/storage/audio/<% out.print(song.getPath()); %>" download>
                                            <p class="w3-tooltip">
                                                <img height="20px" src="${pageContext.request.contextPath}/storage/album/download.png" alt=""/>
                                                <span style="position:absolute;left:0;bottom:18px; border-radius:10px;width:200px" class="w3-text w3-blue w3-tag ">
                                                    Download Song
                                                </span> 
                                            </p>
                                        </a> 
                                        <%
                                        }else {
                                        %>
                                        <a href="">
                                            <p class="w3-tooltip" >
                                                <img height="20px" src="${pageContext.request.contextPath}/storage/album/download.png" alt=""/>
                                                <span style="position:absolute;left:0;bottom:18px; border-radius:10px;width:200px" class="w3-text w3-red w3-tag ">
                                                    You need to pay when downloading this song
                                                </span> 
                                            </p>
                                        </a> 
                                        <%
                                            }
                                        %> 
                                    </div>
                                    <div class="w3-col " name="add" >
                                        <a href="#" onclick="myInformation()">
                                            <p class="w3-tooltip">
                                                <img height="20px" src="${pageContext.request.contextPath}/storage/album/information.png" alt=""/>
                                                <span style="position:absolute;left:0;bottom:18px; border-radius:10px;width:100px" class="w3-text w3-blue w3-tag ">
                                                    Song Info
                                                </span> 
                                            </p> 
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="w3-col w3-container" style="width:25%;">
                            <img class="chiTietAnhCS" src="${pageContext.request.contextPath}/storage/artist/<% out.print(artist.getAvatar()); %>" alt="">
                        </div>
                    </div>
                    <div id="Information" class="w3-row w3-hide ">
                        <div class="w3-col  w3-container" style="width:3%"></div>
                        <div class="w3-col w3-container" style="width:85%">
                            <div >
                                <% out.print(song.getDescription()); %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- col - 4 -->
        </div>               
    </div>

    <div class="bodyMusic">
        <div class="w3-row">
            <div name="loiBaiHat" class="w3-col" style="width:74%">
                <div class="section-chart">
                    <div class="week-chart">           
                        <div class="w3-col w3-container" style="width:28%"></div>
                        <div class="w3-col w3-container" style="width:60%">
                            <div class="zm-navbar">
                                <center>
                                    <ul class="zm-nav-info">                 
                                        <li class="zm-nav-info-item">
                                            <button name="chiMuc" class="w3-bar-item w3-button tablink1 w3-red rr" onclick="openCity1(event, 'London1')">Overview</button>
                                        </li>
                                        <li class="zm-nav-info-item">
                                            <button name="chiMuc" class="w3-bar-item w3-button tablink1" onclick="openCity1(event, 'OtherSong')">Other Song</button>
                                        </li>
                                        <li class="zm-nav-info-item">
                                            <button name="chiMuc" class="w3-bar-item w3-button tablink1" onclick="openCity1(event, 'Tokyo1')">Lyrics</button>
                                        </li>
                                        <li class="zm-nav-info-item">
                                            <button name="chiMuc" class="w3-bar-item w3-button tablink1" onclick="openCity1(event, 'a1')">MV</button>
                                        </li>
                                    </ul>
                                </center>                             
                            </div>
                        </div>
                        <div class="w3-col w3-container" style="width:12%"></div>
                        <div>
                            <div id="London1" class="w3-container city1" >
                                <div>
                                    <div class="section-header">
                                        Top 10 Featured Songs:
                                    </div>
                                    <div class="w3-col w3-container" style="width:98%">
                                        <div class="w3-col" style="width:35%">
                                            <div class="w3-section" style="max-width:230px">
                                                <div style="text-align:right">
                                                    <%
                                                        List lsusa = new ArrayList();
                                                        lsusa = (ArrayList) request.getAttribute("listSongOfArtistTop5");
                                                        for (int idx = 0; idx < lsusa.size(); idx++) {
                                                            String tsa = lsusa.get(idx).toString();
                                                            char[] chsa = tsa.substring(1, tsa.indexOf("]")).toCharArray();
                                                            String AccountIDsa, SongIDsa, SongNamesa, Pathsa, PriceSongsa, ngheSiIDsa, tenNgheSisa, anhBiaHatsa;
                                                            String catsa = tsa.substring(1, tsa.indexOf("]")).trim();
                                                            if (!catsa.substring(0, catsa.indexOf("/")).equals("null")) {
                                                                SongIDsa = catsa.substring(0, catsa.indexOf("/"));
                                                                String cat2sa = catsa.substring(catsa.indexOf("/") + 1).trim();
                                                                //
                                                                SongNamesa = cat2sa.substring(0, cat2sa.indexOf("/"));
                                                                String cat3sa = cat2sa.substring(cat2sa.indexOf("/") + 1).trim();
                                                                //
                                                                Pathsa = cat3sa.substring(0, cat3sa.indexOf("/"));
                                                                String cat4sa = cat3sa.substring(cat3sa.indexOf("/") + 1).trim();
                                                                //
                                                                PriceSongsa = cat4sa.substring(0, cat4sa.indexOf("/"));
                                                                String cat5sa = cat4sa.substring(cat4sa.indexOf("/") + 1).trim();
                                                                //
                                                                ngheSiIDsa = cat5sa.substring(0, cat5sa.indexOf("/"));
                                                                String cat6sa = cat5sa.substring(cat5sa.indexOf("/") + 1).trim();
                                                                //
                                                                anhBiaHatsa = cat6sa.substring(0, cat6sa.indexOf("/"));
                                                                String cat7sa = cat6sa.substring(cat6sa.indexOf("/") + 1).trim();
                                                    %>
                                                    <img  class="mySlides" src="${pageContext.request.contextPath}<% out.print("/storage/song/" + anhBiaHatsa);%>" style="width:100%;border-radius:5px">
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </div>          
                                            </div>
                                        </div>
                                        <div class="w3-col" style="width:65%">
                                            <div name="dsMusicNoiBat" class="music-container">
                                                <div class="music-container">
                                                    <div class="chart-music" >   
                                                        <div class="chart-music"  style="max-height:250px">              
                                                            <table class="music-list-chart">
                                                                <tbody id="myTable" class="list-week-chart">
                                                                    <%
                                                                        //lay id khac hang 
                                                                        String idSongOrdera = null;
                                                                        if (session.getAttribute("accountID") != null) {
                                                                            idSongOrdera = String.valueOf(request.getAttribute("idSongOrder"));
                                                                        } else {
                                                                            idSongOrdera = null;
                                                                        }
                                                                        //String AccountIDSession = String.valueOf(session.getAttribute("userID"));
                                                                        List lsu = new ArrayList();
                                                                        lsu = (ArrayList) request.getAttribute("listSongOfArtistTop5");
                                                                        for (int idx = 0; idx < lsu.size(); idx++) {
                                                                            String t = lsu.get(idx).toString();
                                                                            char[] ch = t.substring(1, t.indexOf("]")).toCharArray();
                                                                            String AccountID, SongID, SongName, Path, PriceSong, ngheSiID, tenNgheSi, anhBiaHat, trangThaiThanhToan, TrangThaiOrder, View, countLike;
                                                                            String cat = t.substring(1, t.indexOf("]")).trim();
                                                                            if (!cat.substring(0, cat.indexOf("/")).equals("null")) {
                                                                                SongID = cat.substring(0, cat.indexOf("/"));
                                                                                String cat2 = cat.substring(cat.indexOf("/") + 1).trim();
                                                                                //
                                                                                SongName = cat2.substring(0, cat2.indexOf("/"));
                                                                                String cat3 = cat2.substring(cat2.indexOf("/") + 1).trim();
                                                                                //
                                                                                Path = cat3.substring(0, cat3.indexOf("/"));
                                                                                String cat4 = cat3.substring(cat3.indexOf("/") + 1).trim();
                                                                                //
                                                                                PriceSong = cat4.substring(0, cat4.indexOf("/"));
                                                                                String cat5 = cat4.substring(cat4.indexOf("/") + 1).trim();
                                                                                //
                                                                                ngheSiID = cat5.substring(0, cat5.indexOf("/"));
                                                                                String cat6 = cat5.substring(cat5.indexOf("/") + 1).trim();
                                                                                //
                                                                                anhBiaHat = cat6.substring(0, cat6.indexOf("/"));
                                                                                String cat7 = cat6.substring(cat6.indexOf("/") + 1).trim();
                                                                                //
                                                                                tenNgheSi = cat7.substring(0, cat7.indexOf("/"));
                                                                                String cat8 = cat7.substring(cat7.indexOf("/") + 1).trim();
                                                                                //
                                                                                View = cat8.substring(0, cat8.indexOf("/"));
                                                                                String cat9 = cat8.substring(cat8.indexOf("/") + 1).trim();
                                                                                //
                                                                                countLike = cat9;
                                                                    %>
                                                                    <tr class="itemsMusic">
                                                                        <td style="width:40%;text-align: left;">
                                                                            <div class="chart-info">
                                                                                <div class="chart-thumbnail">
                                                                                    <img src="${pageContext.request.contextPath}<% out.print("/storage/song/" + anhBiaHat);%>" alt="">
                                                                                </div>
                                                                                <div class="chart-name">
                                                                                    <a href="${pageContext.request.contextPath}/SongDetail?songID=<% out.print(SongID); %>&idArtist=<% out.print(ngheSiID); %>">
                                                                                        <span class="chart-title"><% out.print(SongName); %></span>
                                                                                    </a>
                                                                                    <a href="${pageContext.request.contextPath}/ClientArtist?do=info&id=<% out.print(ngheSiID); %>">
                                                                                        <span class="chart-singer"><% out.print(tenNgheSi); %></span>
                                                                                    </a>
                                                                                </div> 
                                                                            </div>
                                                                        </td>
                                                                        <td style="width:25%">
                                                                            <span><i class='red bx bx-headphone'></i></span>
                                                                            <span><% out.print(View); %></span>
                                                                        </td>
                                                                        <td style="width:15%">
                                                                            <span class="chart-like">
                                                                                <i class='bx bx-heart red'></i><% out.print(countLike); %>
                                                                            </span>
                                                                        </td>
                                                                        <td style="width:35%;text-align: right" class="chart-action">

                                                                            <%
                                                                                if (idSongOrdera.indexOf(SongID) != -1) {
                                                                            %>
                                                                            <a href="" data-id="<% out.print(SongID);%>" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                                                            <%
                                                                            } else {
                                                                                if (PriceSong.equals("null") || PriceSong.equals("0")) {
                                                                            %>

                                                                            <a href="" data-id="<% out.print(SongID); %>" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                                                            <%
                                                                            } else if (!PriceSong.equals("null") || !PriceSong.equals("0")) {
                                                                            %>
                                                                            <a  onclick="document.getElementById('id01').style.display = 'block';
                                                                                    loatOrder(<% out.print(SongID); %>)"  href="#" class="btn-link btn-order"> Buy <% out.print(PriceSong); %>$</a>
                                                                            <%
                                                                                    }

                                                                                }
                                                                            %>

                                                                        </td>
                                                                    </tr>
                                                                    <%   }
                                                                        }
                                                                    %> 
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="w3-col w3-container" style="width:98%">
                                        <div class="section-header">
                                            MAY YOU LIKE
                                        </div>
                                        <div class="artist-slide carousel-nav-center owl-carousel">
                                            <!--Java-->
                                            <%
                                                lsu = (ArrayList) request.getAttribute("artistsTop");
                                                for (int idx = 0; idx < lsu.size(); idx++) {
                                                    String t = lsu.get(idx).toString();
                                                    char[] ch = t.substring(1, t.indexOf("]")).toCharArray();
                                                    String artistsID, artistsName, artistsFullName, artistsAnh;
                                                    String cat = t.substring(1, t.indexOf("]")).trim();
                                                    if (!cat.substring(0, cat.indexOf("/")).equals("null")) {
                                                        artistsID = cat.substring(0, cat.indexOf("/"));
                                                        String cat2 = cat.substring(cat.indexOf("/") + 1).trim();
                                                        //
                                                        artistsName = cat2.substring(0, cat2.indexOf("/"));
                                                        String cat3 = cat2.substring(cat2.indexOf("/") + 1).trim();
                                                        //
                                                        artistsFullName = cat3.substring(0, cat3.indexOf("/"));
                                                        String cat4 = cat3.substring(cat3.indexOf("/") + 1).trim();
                                                        //
                                                        artistsAnh = cat4;
                                            %>
                                            <!-- MOVIE ITEM -->
                                            <div class="suggest-artist">
                                                <a href="${pageContext.request.contextPath}/ClientArtist?do=info&id=<% out.print(artistsID); %>" class="suggest-artist-avatar">
                                                    <img src="${pageContext.request.contextPath}/storage/artist/<% out.print(artistsAnh); %>" alt="">
                                                </a>
                                                <p><a href="${pageContext.request.contextPath}/ClientArtist?do=info&id=<% out.print(artistsID); %>"><% out.print(artistsName); %> </a></p>
                                                <div class="artist-button">
                                                    <button class="is-small"><i class='bx bx-user-plus'></i>FOLLOW</button>
                                                </div>
                                            </div>
                                            <!-- END MOVIE ITEM -->
                                            <%
                                                    }
                                                }
                                            %>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="OtherSong" class="w3-container city1" style="display:none">
                                <div class="section-header">
                                    Featured Songs Of:  
                                </div>
                                <div class="music-container">
                                    <div class="chart-music"  id="playList">   
                                        <div class="chart-music">              
                                            <table class="music-list-chart">
                                                <tbody id="myTable" class="list-week-chart">
                                                    <%
                                                        //lay id khac hang 
                                                        // String idSongOrdera = null;
                                                        if (session.getAttribute(
                                                                "accountID") != null) {
                                                            idSongOrdera = String.valueOf(request.getAttribute("idSongOrder"));
                                                        } else {
                                                            idSongOrdera = null;
                                                        }
                                                        //String AccountIDSession = String.valueOf(session.getAttribute("userID"));
                                                        //List lsu = new ArrayList();
                                                        lsu = (ArrayList) request.getAttribute("listSongOfArtist");
                                                        for (int idx = 0;
                                                                idx < lsu.size();
                                                                idx++) {
                                                            String t = lsu.get(idx).toString();
                                                            char[] ch = t.substring(1, t.indexOf("]")).toCharArray();
                                                            String AccountID, SongID, SongName, Path, PriceSong, ngheSiID, tenNgheSi, anhBiaHat, trangThaiThanhToan, TrangThaiOrder, View, countLike;
                                                            String cat = t.substring(1, t.indexOf("]")).trim();
                                                            if (!cat.substring(0, cat.indexOf("/")).equals("null")) {
                                                                SongID = cat.substring(0, cat.indexOf("/"));
                                                                String cat2 = cat.substring(cat.indexOf("/") + 1).trim();
                                                                //
                                                                SongName = cat2.substring(0, cat2.indexOf("/"));
                                                                String cat3 = cat2.substring(cat2.indexOf("/") + 1).trim();
                                                                //
                                                                Path = cat3.substring(0, cat3.indexOf("/"));
                                                                String cat4 = cat3.substring(cat3.indexOf("/") + 1).trim();
                                                                //
                                                                PriceSong = cat4.substring(0, cat4.indexOf("/"));
                                                                String cat5 = cat4.substring(cat4.indexOf("/") + 1).trim();
                                                                //
                                                                ngheSiID = cat5.substring(0, cat5.indexOf("/"));
                                                                String cat6 = cat5.substring(cat5.indexOf("/") + 1).trim();
                                                                //
                                                                anhBiaHat = cat6.substring(0, cat6.indexOf("/"));
                                                                String cat7 = cat6.substring(cat6.indexOf("/") + 1).trim();
                                                                //
                                                                tenNgheSi = cat7.substring(0, cat7.indexOf("/"));
                                                                String cat8 = cat7.substring(cat7.indexOf("/") + 1).trim();
                                                                //
                                                                View = cat8.substring(0, cat8.indexOf("/"));
                                                                String cat9 = cat8.substring(cat8.indexOf("/") + 1).trim();
                                                                //
                                                                countLike = cat9;
                                                    %>
                                                    <tr class="itemsMusic">
                                                        <td style="width:30%;text-align: left;">
                                                            <div class="chart-info">
                                                                <div class="chart-thumbnail">
                                                                    <img src="${pageContext.request.contextPath}<% out.print("/storage/song/" + anhBiaHat);%>" alt="">
                                                                </div>
                                                                <div class="chart-name">
                                                                    <a href="${pageContext.request.contextPath}/SongDetail?songID=<% out.print(SongID); %>&idArtist=<% out.print(ngheSiID); %>">
                                                                        <span class="chart-title"><% out.print(SongName); %></span>
                                                                    </a>
                                                                    <a href="${pageContext.request.contextPath}/ClientArtist?do=info&id=<% out.print(ngheSiID); %>">
                                                                        <span class="chart-singer"><% out.print(tenNgheSi); %></span>
                                                                    </a>
                                                                </div> 
                                                            </div>
                                                        </td>
                                                        <td style="width:25%">
                                                            <span><i class='red bx bx-headphone'></i></span>
                                                            <span><% out.print(View); %></span>
                                                        </td>
                                                        <td style="width:15%">
                                                            <span class="chart-like">
                                                                <i class='bx bx-heart red'></i><% out.print(countLike); %>
                                                            </span>
                                                        </td>
                                                        <td style="width:50%;text-align: right" class="chart-action">

                                                            <%
                                                                if (idSongOrdera.indexOf(SongID) != -1) {
                                                            %>
                                                            <a href="" data-id="<% out.print(SongID);%>" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                                            <%
                                                            } else {
                                                                if (PriceSong.equals("null") || PriceSong.equals("0")) {
                                                            %>

                                                            <a href="" data-id="<% out.print(SongID); %>" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                                            <%
                                                            } else if (!PriceSong.equals("null") || !PriceSong.equals("0")) {
                                                            %>
                                                            <a onclick="document.getElementById('id01').style.display = 'block';
                                                                    loatOrder(<% out.print(SongID); %>)"  href="#" class="btn-link btn-order"> Buy <% out.print(PriceSong); %>$</a>
                                                            <%
                                                                    }

                                                                }
                                                            %>

                                                        </td>
                                                    </tr>
                                                    <%   }
                                                        }
                                                    %> 
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="Tokyo1" class="w3-container city1" style="display:none">
                                <div>
                                    <div class="section-header">
                                        Lyrics
                                    </div>
                                    <div class="thanhCuon"> 
                                        <div id="loiBaiHat" class="loiBaiHat">
                                            <%
                                                String loi = song.getLyrics();
                                                if (loi != null) {
                                                    out.print(loi);
                                                } else {
                                            %>
                                            <center>
                                                <p style="height:120px;font-size:100px">X</p>
                                                List is empty
                                            </center>
                                            <%
                                                }
                                            %>                          
                                        </div>
                                    </div>
                                    <!--
                                             <div class="docAnHien">
                                                      <br>
                                                      <b>
                                                          <button type="button" class="anHien" id="hien" onclick="hien()">Show all...</button>
                                                          <button type="button" class="anHien" id="an" onclick="an()">Hide Less...</button>
                                                      </b>
                                                  </div> 
                                    -->     
                                </div> 
                            </div>
                            <div id="a1" class="w3-container city1" style="display:none">
                                <div class="noiDungTrong">
                                    <center>
                                        <p style="height:120px;font-size:100px">X</p>
                                        List is empty
                                    </center>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="w3-col" style="width:26%">
                <div class="section-chart">
                    <div name="week-chart" class="week-chart">
                        <div class="chiMuc">
                            <div class="w3-row">
                                <div class="w3-col " style="width:0%"></div>
                                <div class="w3-col " style="width:100%">
                                    <div class="zm-navbar">
                                        <ul class="zm-nav-info">                 
                                            <li class="zm-nav-info-item">
                                                <button name="chiMuc" class="w3-bar-item w3-button tablink w3-red" onclick="openCity(event, 'London')">Suggestions</button>
                                            </li>
                                            <li class="zm-nav-info-item">
                                                <button name="chiMuc" class="w3-bar-item w3-button tablink" style="width:150px" onclick="openCity(event, 'Paris')"> Favorite Albums</button>
                                            </li>                                  
                                        </ul>
                                    </div>
                                </div>
                                <div class="w3-col " style="width:0%"></div>
                            </div>
                            <div id="London" class="w3-container city">
                                <div name="" class="music-container">
                                    <div class="">
                                        <div class="chart-music" >   
                                            <div class="chart-music">              
                                                <table class="music-list-chart">
                                                    <tbody id="myTable" class="list-week-chart">
                                                        <%
                                                            //lay id khac hang 
                                                            if (session.getAttribute(
                                                                    "accountID") != null) {
                                                                idSongOrdera = String.valueOf(request.getAttribute("idSongOrder"));
                                                            } else {
                                                                idSongOrdera = null;
                                                            }
                                                            //String AccountIDSession = String.valueOf(session.getAttribute("userID"));
                                                            //List lsu = new ArrayList();
                                                            lsu = (ArrayList) request.getAttribute("listAllSongAndArtist");
                                                            for (int idx = 0;
                                                                    idx < lsu.size();
                                                                    idx++) {
                                                                String t = lsu.get(idx).toString();
                                                                char[] ch = t.substring(1, t.indexOf("]")).toCharArray();
                                                                String AccountID, SongID, SongName, Path, PriceSong, ngheSiID, tenNgheSi, anhBiaHat, trangThaiThanhToan, View;
                                                                String cat = t.substring(1, t.indexOf("]")).trim();
                                                                if (!cat.substring(0, cat.indexOf("/")).equals("null")) {
                                                                    SongID = cat.substring(0, cat.indexOf("/"));
                                                                    String cat2 = cat.substring(cat.indexOf("/") + 1).trim();
                                                                    //
                                                                    SongName = cat2.substring(0, cat2.indexOf("/"));
                                                                    String cat3 = cat2.substring(cat2.indexOf("/") + 1).trim();
                                                                    //
                                                                    Path = cat3.substring(0, cat3.indexOf("/"));
                                                                    String cat4 = cat3.substring(cat3.indexOf("/") + 1).trim();
                                                                    //
                                                                    PriceSong = cat4.substring(0, cat4.indexOf("/"));
                                                                    String cat5 = cat4.substring(cat4.indexOf("/") + 1).trim();
                                                                    //
                                                                    ngheSiID = cat5.substring(0, cat5.indexOf("/"));
                                                                    String cat6 = cat5.substring(cat5.indexOf("/") + 1).trim();
                                                                    //
                                                                    anhBiaHat = cat6.substring(0, cat6.indexOf("/"));
                                                                    String cat7 = cat6.substring(cat6.indexOf("/") + 1).trim();
                                                                    //
                                                                    tenNgheSi = cat7;
                                                        %>

                                                        <tr class="itemsMusic">
                                                            <td style="width:65%;text-align: left;">
                                                                <div class="chart-info">
                                                                    <div class="chart-thumbnail">
                                                                        <img src="${pageContext.request.contextPath}<% out.print("/storage/song/" + anhBiaHat);%>" alt="">
                                                                    </div>
                                                                    <div class="chart-name">
                                                                        <a href="${pageContext.request.contextPath}/SongDetail?songID=<% out.print(SongID); %>&idArtist=<% out.print(ngheSiID); %>">
                                                                            <span class="chart-title"><% out.print(SongName); %></span>
                                                                        </a>
                                                                        <a href="${pageContext.request.contextPath}/ClientArtist?do=info&id=<% out.print(ngheSiID); %>">
                                                                            <span class="chart-singer"><% out.print(tenNgheSi); %></span>
                                                                        </a>
                                                                    </div> 
                                                                </div>
                                                            </td>
                                                            <td></td>
                                                            <td></td>
                                                            <td style="width:35%;text-align: right" class="chart-action">
                                                                <%
                                                                    if (idSongOrdera.indexOf(SongID) != -1) {
                                                                %>

                                                                <a href="" data-id="<% out.print(SongID);%>" class="btn-link btn-play" style="background:none;border:0px;height:20px">
                                                                    <img style="height:20px"src="${pageContext.request.contextPath}/storage/song/iconplay.png" alt=""/>
                                                                </a>
                                                                <%
                                                                } else {
                                                                    if (PriceSong.equals("null") || PriceSong.equals("0")) {
                                                                %>

                                                                <a href="" data-id="<% out.print(SongID);%>" class="btn-link btn-play" style="background:none;border:0px;height:20px">
                                                                    <img style="height:20px"src="${pageContext.request.contextPath}/storage/song/iconplay.png" alt=""/>
                                                                </a>
                                                                <%
                                                                } else if (!PriceSong.equals("null") || !PriceSong.equals("0")) {
                                                                %>
                                                                <a  onclick="document.getElementById('id01').style.display = 'block';
                                                                        loatOrder(<% out.print(SongID); %>)"  href="#" class="btn-link btn-order"  style="background:none;border:0px"><% out.print(PriceSong); %>$</a>
                                                                <%
                                                                        }

                                                                    }
                                                                %>
                                                            </td>
                                                        </tr>
                                                        <%   }
                                                            }
                                                        %> 
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="Paris" class="w3-container city" style="display:none"></div>  
                        </div> 
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function myInformation() {
        var x = document.getElementById('Information');
        if (x.className.indexOf("w3-show") == -1) {
            x.className += " w3-show";
        } else {
            x.className = x.className.replace(" w3-show", "");
        }
    }
</script>



<script>
    var myIndex = 0;
    carousel();

    function carousel() {
        var i;
        var x = document.getElementsByClassName("mySlides");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        myIndex++;
        if (myIndex > x.length) {
            myIndex = 1
        }
        x[myIndex - 1].style.display = "block";
        setTimeout(carousel, 2000); // Change image every 2 seconds
    }
</script>
<%@include file="../includes/footer.jsp"%>