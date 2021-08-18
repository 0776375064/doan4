$(document).ready(() => {


    $(".playlist").on('click', function (e) {
        e.preventDefault();
        $(this).toggleClass("active");
        $(".playlist-navbar").toggleClass("open");
    });

    $('#hamburger-menu').click(() => {
        $('#hamburger-menu').toggleClass('active')
        $('#nav-menu').toggleClass('active')
    });

    //drop down
    window.onclick = function (event) {
        openCloseDropdown(event);
    }

// close all dropdown
    function closeAllDropdown() {
        var dropdowns = document.querySelectorAll('.dropdown-expand');
        dropdowns.forEach(drops => {
            drops.classList.remove('dropdown-expand');
        })
    }

    function openCloseDropdown(event) {
        if (!event.target.matches('.dropdown-toggle')) {
            // close dropdown when click outside menu
            closeAllDropdown();
        } else {
            var toggle = event.target.dataset.toggle;
            //console.log(toggle);
            var content = document.querySelector(`#${toggle}`);
            //console.log(content);
            if (content.classList.contains('dropdown-expand')) {
                closeAllDropdown();
            } else {
                closeAllDropdown();
                content.classList.add('dropdown-expand');
            }
        }
    }

    /// play music
    $(document).on('click', '.btn-play', function (e) {
        e.preventDefault();
        var idSong = $(this).attr("data-id");
        $("#songID").val(idSong);
        sttBefore = idSong;
        sttPlay = 0;
        //console.log(idSong);
        $.ajax({
            url: $("#url").val() + "/player?do=listen",
            cache: false,
            type: 'get',
            dataType: 'json',
            data: {
                id: idSong
            },
            success: function (result) {
                console.log(result);
                if (result.msg == "login") {
                    Swal.fire(
                            'You do not have permission to play this song',
                            'Please login to use our service!',
                            'error'
                            );
                } else if (result.msg == "bought") {
                    Swal.fire({
                        title: 'You do not have permission to play this song',
                        text: "You have to buy this song before play it.",
                        icon: 'error',
                        showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        confirmButtonText: 'Buy'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location = window.location.protocol + "//" + window.location.host + $("#url").val() + "/SongDetail?songID=" + idSong;
                        }
                    })
                } else if (result.msg == "success") {
                    playlist = result.value;
                    console.log(playlist);
                    var path = $("#url").val() + "/storage/audio/" + result.path;
                    pathAudio = window.location.protocol + "//" + window.location.host + $("#url").val() + "/storage/audio/" + result.path;
                    //console.log(path);
                    $("#music_src").attr('src', path);
                    $(".audioplayer").remove();
                    var audio = document.createElement('AUDIO');
                    audio.setAttribute('controls', true);
                    var source = document.createElement('source');
                    source.src = path;
                    audio.appendChild(source)
                    $('.player-control_center').append(audio);
                    var thumbnail = $("#url").val() + "/storage/song/" + result.thumbnail;
                    $('.player-control_left').find(".thumbnail-music").attr('src', thumbnail);
                    $('.player-control_left').find(".media-title-name").html(result.name);
                    $('.player-control_left').find(".media-title-singer").html(result.artistName);
                    $('.player-control_left').find(".media-title a").attr('href', window.location.protocol + "//" + window.location.host + $("#url").val() + "/SongDetail?songID=" + idSong);
                    $('.player-control_left').find(".media-subtitle a").attr('href', window.location.protocol + "//" + window.location.host + $("#url").val() + "/ClientArtist?do=info&id=" + result.artist);
                    $('.player-control_right').find(".like-song span").html(result.like);
                    $('.player-control_right').find(".cmt-song span").html(result.cmt);
                    if (result.like < 1) {
                        $('.modal').find(".modal-action h3").html(result.cmt + " comment");
                    } else {
                        $('.modal').find(".modal-action h3").html(result.cmt + " comments");
                    }
                    if (result.slike == "yes") {
                        $(".like-song").find("i").removeClass("bx-heart").addClass("bxs-heart red");
                    } else {
                        $(".like-song").find("i").removeClass("bxs-heart red").addClass("bx-heart");
                    }
                    $(".zm-contextMenu").find(".media-left").find(".is-40-40").attr('src', thumbnail);
                    $(".zm-contextMenu").find(".media-content").find(".media-title a").html(result.name);
                    $(".zm-contextMenu").find(".media-content").find(".stat-item:nth-child(1)").find("span").html(result.like);
                    $(".zm-contextMenu").find(".media-content").find(".stat-item:nth-child(2)").find("span").html(result.view);
                    $("#textLyric").html("");
                    $("#textLyric").html(result.lyrics);
                    $(".fullscreen-track ").find("img").attr('src', thumbnail);
                    $(".fullscreen-lyrics").html(result.lyrics);
                    $(".fullscreen-songname").html(result.name);
                    $('.fullscreen-artist').html(result.artistName);
                    $(function () {
                        $('audio').audioPlayer();
                    });
                } else {
                    Swal.fire(
                            'Error',
                            'We are not found this song, please try again!',
                            'error'
                            );
                }
            },
            error: function () {
                alert("error");
            }
        }); //end ajax play song
        $(".playlist-content-item").removeClass("active");
        $("#pl" + idSong).addClass("active");
    });

    // toggle music
    // toogle music player
    $(".zm-tooltip").on('click', function () {
        $(this).toggleClass('zm-active');
    });

    // download file
    $(".group-button-list .zm-btn:nth-child(1)").on('click', function (e) {
        Swal.fire({
            title: 'Are you sure?',
            text: "Do you want to download this song?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes!'
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire(
                        'Success!',
                        'Your file has been downloading.',
                        'success'
                        );
                window.location = pathAudio;

            }
        })
    });

    // view lyric
    $(".group-button-list .zm-btn:nth-child(2)").on('click', function (e) {
        $("#lyricPOp").addClass("open");
    });

    // copy song url
    $(".zm-copy").on('click', function () {
        var url = window.location.host + "/clientSong?id=" + $("#songID").val();
        $("#urlsong").val(url);
        var copyText = $("#urlsong").val();
        navigator.clipboard.writeText(copyText);
        /* Select the text field */
        Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Copy link to clipboard',
            showConfirmButton: false,
            timer: 1500
        })
    });

    // add to favorite
    $(".zm-favorite").on('click', function () {
        $.ajax({
            url: $("#url").val() + "/player?do=favorite",
            cache: false,
            type: 'get',
            dataType: 'json',
            data: {
                addAlbum: $("#songID").val()
            },
            success: function (result) {
                console.log(result);
                if (result != null && result.msg == "success") {
                    Swal.fire(
                            'Success!',
                            'Added this song to favorites.',
                            'success'
                            );
                }
            },
            error: function () {
                Swal.fire('Error!', 'We cant handling this request right now.', 'error');
            }
        });
    });

    // share to social media
    $(".share-content .menu-list .facebook").on('click', function (e) {
        var url = window.location.protocol + "//" + window.location.host + $("#url").val() + "/SongDetail?songID=" + $("#songID").val();
        var share = "http://www.facebook.com/sharer/sharer.php?u=" + url + "&t=Bugs - Music for life";
        console.log(share);
        window.location = share;
    });
    $(".share-content .menu-list .twitter").on('click', function (e) {
        var url = window.location.protocol + "//" + window.location.host + $("#url").val() + "/SongDetail?songID=" + $("#songID").val();
        var share = "http://www.twitter.com/intent/tweet?url=" + url + "&via=TWITTER_HANDLE_HERE&text=Bugs - Music for life";
        console.log(share);
        window.location = share;
    });

    // like song
    $('.like-song').on('click', function (e) {
        e.preventDefault();
        var timbay = document.querySelector(".timbay");
        timbay.classList.toggle("active");
        setTimeout(function () {
            closeTimbay();
        }, 1000);
        if ($(this).find("i").hasClass("bx-heart")) {
            $(this).find("i").removeClass("bx-heart").addClass("bxs-heart red");
        } else {
            $(this).find("i").removeClass("bxs-heart red").addClass("bx-heart");
        }
        $.ajax({
            url: $("#url").val() + "/player?do=like",
            cache: false,
            type: 'get',
            dataType: 'json',
            data: {
                id: $("#songID").val()
            },
            success: function (result) {
                console.log(result);
                if (result != null && result.msg != null) {
                    $('.player-control_right').find(".like-song span").html(result.numlike);
                }
            },
            error: function () {
                alert("Error");
            }
        });
    });

    // modal
    $(".modal-click").on('click', (e) => {
        e.preventDefault();
        $(".comment-content").html("");
        $("#listCMT").toggleClass("open");
        $.ajax({
            url: $("#url").val() + "/player?do=review",
            cache: false,
            type: 'get',
            dataType: 'text',
            data: {
                id: $("#songID").val()
            },
            success: function (result) {
                if (result != null) {
                    $(".comment-content").append(result);
                }
            },
            error: function () {
                alert("Error");
            }
        });
    });
    $(".modal-close").on('click', (e) => {
        e.preventDefault();
        $(".modal").removeClass("open");
    });
    $(".modal").on('click', (e) => {
        var div = e.target.classList;
        if (div.contains("modal")) {
            $(".modal").removeClass("open");
        }
    });


    // delete my comment
    $(document).on("click", ".media-delete", function (e) {
        var id = $(this).attr("data-cmt");
        console.log(id);
        $.ajax({
            url: $("#url").val() + "/player?do=delcomment",
            cache: false,
            type: 'get',
            dataType: 'json',
            data: {
                id: id
            },
            success: function (result) {
                if (result.msg == "success") {
                    $("#cmt" + id).fadeOut(500);
                } else {
                    Swal.fire('Error!', 'We cant handling this request right now.', 'error');
                }
            },
            error: function () {
                alert("Cannot handling this request");
            }
        });
    });

    // login

    var phanhoi = document.querySelector('.phanhoi-container');
    var closePhanhoi = document.querySelector('.close-phanhoi');
    $(".login-btn").on('click', function (e) {
        e.preventDefault();
        $(".phanhoi-container").toggleClass('actives-phanhoi');
    });
    if (phanhoi) {
        phanhoi.addEventListener('click', function (e) {
            var div = e.target;
            if (div.classList.contains('phanhoi-container')) {
                phanhoi.classList.remove('actives-phanhoi');
            }
        });
    }
    if (closePhanhoi) {
        closePhanhoi.addEventListener('click', function (e) {
            phanhoi.classList.remove('actives-phanhoi');
        });
    }

    // login request
    $("#frmLogin").submit(function (event) {
        $.ajax({
            url: $("#url").val() + "/clientAccount?do=login",
            cache: false,
            type: 'get',
            dataType: 'json',
            data: {
                username: $("#username").val(),
                password: $("#password").val()
            },
            success: function (result) {
                console.log(result);
                if (result != null) {
                    $(".phanhoi-error").html(result.msg);
                    if (result.status == "success") {
                        location.reload();
                    }
                }
            },
            error: function () {
                Swal.fire(
                        'Error?',
                        'Occur error when login...!',
                        'error'
                        );
            }
        });
        return false;
    });
    /// login to buy
    $(".btn-per").on('click', function (e) {
        e.preventDefault();
        Swal.fire(
                'You do not have permission',
                'Please, login to buy this song',
                'error'
                );
    });

    // setting owl carousel

    let navText = ["<i class='bx bx-chevron-left'></i>", "<i class='bx bx-chevron-right'></i>"]

    $('#hero-carousel').owlCarousel({
        items: 1,
        dots: false,
        loop: true,
        nav: true,
        navText: navText,
        autoplay: true,
        autoplayHoverPause: true
    });

    $('.news-slide').owlCarousel({
        items: 2,
        dots: false,
        nav: true,
        navText: navText,
        margin: 15,
        responsive: {
            500: {
                items: 2
            },
            1280: {
                items: 4
            },
            1600: {
                items: 6
            }
        }
    });

    $('.artist-slide').owlCarousel({
        items: 2,
        dots: false,
        nav: true,
        navText: navText,
        margin: 15,
        responsive: {
            500: {
                items: 3
            },
            1280: {
                items: 6
            },
            1600: {
                items: 6
            }
        }
    });

    $(".fullscreen-tab-item").on('click', function (e) {
        $(".fullscreen-tab-item").removeClass("is-active");
        $(this).addClass("is-active");
        var item = $(this).attr("data-item");
        console.log(item);
        $(".fullscreen-jtem").removeClass("open").addClass("hide");
        $("." + item).addClass("open");
    });

// expend full screen
    $(".expend-fullcreen").on('click', function (e) {
        e.preventDefault();
        $(".fullscreen-fixed").toggleClass("open");
    });
    $(".fullscreen-close").on('click', function (e) {
        $(".fullscreen-fixed").removeClass("open");
    });

});

// close timbay
function closeTimbay() {
    var timbay = document.querySelector(".timbay");
    timbay.classList.remove("active");
}
var pathAudio; // path of audio
var sttPlay = 0; // current index of song in array object
var sttBefore = 0; /// id song account click