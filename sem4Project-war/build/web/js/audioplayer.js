/*
 AUTHOR: Osvaldas Valutis, www.osvaldas.info
 */



;
(function ($, window, document, undefined)
{
    var isTouch = 'ontouchstart' in window,
            eStart = isTouch ? 'touchstart' : 'mousedown',
            eMove = isTouch ? 'touchmove' : 'mousemove',
            eEnd = isTouch ? 'touchend' : 'mouseup',
            eCancel = isTouch ? 'touchcancel' : 'mouseup',
            secondsToTime = function (secs)
            {
                var hours = Math.floor(secs / 3600), minutes = Math.floor(secs % 3600 / 60), seconds = Math.ceil(secs % 3600 % 60);
                return (hours == 0 ? '' : hours > 0 && hours.toString().length < 2 ? '0' + hours + ':' : hours + ':') + (minutes.toString().length < 2 ? '0' + minutes : minutes) + ':' + (seconds.toString().length < 2 ? '0' + seconds : seconds);
            },
            canPlayType = function (file)
            {
                var audioElement = document.createElement('audio');
                return !!(audioElement.canPlayType && audioElement.canPlayType('audio/' + file.split('.').pop().toLowerCase() + ';').replace(/no/, ''));
            };

    $.fn.audioPlayer = function (params)
    {
        var params = $.extend({classPrefix: 'audioplayer', strPlay: 'Play', strPause: 'Pause', strVolume: 'Volume'}, params),
                cssClass = {},
                cssClassSub =
                {
                    playPause: 'playpause',
                    playing: 'playing',
                    time: 'time',
                    timeCurrent: 'time-current',
                    timeDuration: 'time-duration',
                    bar: 'bar',
                    barLoaded: 'bar-loaded',
                    barPlayed: 'bar-played',
                    volume: 'volume',
                    volumeButton: 'volume-button',
                    volumeAdjust: 'volume-adjust',
                    noVolume: 'novolume',
                    mute: 'mute',
                    mini: 'mini'
                };

        for (var subName in cssClassSub)
            cssClass[ subName ] = params.classPrefix + '-' + cssClassSub[ subName ];

        this.each(function ()
        {
            if ($(this).prop('tagName').toLowerCase() != 'audio')
                return false;

            var $this = $(this),
                    audioFile = $this.attr('src'),
                    isAutoPlay = $this.get(0).getAttribute('autoplay'), isAutoPlay = isAutoPlay === '' || isAutoPlay === 'autoplay' ? true : false,
                    isLoop = $this.get(0).getAttribute('loop'), isLoop = isLoop === '' || isLoop === 'loop' ? true : false,
                    isSupport = false;

            if (typeof audioFile === 'undefined')
            {
                $this.find('source').each(function ()
                {
                    audioFile = $(this).attr('src');
                    if (typeof audioFile !== 'undefined' && canPlayType(audioFile))
                    {
                        isSupport = true;
                        return false;
                    }
                });
            } else if (canPlayType(audioFile))
                isSupport = true;

            var thePlayer = $('<div class="' + params.classPrefix + '">' + (isSupport ? $('<div>').append($this.eq(0).clone()).html() : '<embed src="' + audioFile + '" width="0" height="0" volume="100" autostart="' + isAutoPlay.toString() + '" loop="' + isLoop.toString() + '" />') + '<div class="' + cssClass.playPause + '" title="' + params.strPlay + '"><a href="#">' + params.strPlay + '</a></div></div>'),
                    theAudio = isSupport ? thePlayer.find('audio') : thePlayer.find('embed'), theAudio = theAudio.get(0);
            // custom by duy
            theAudio.play();
            thePlayer.addClass(cssClass.playing);
            $("body").addClass('listen').addClass("playing");

            if (isSupport)
            {
                thePlayer.find('audio').css({'width': 0, 'height': 0, 'visibility': 'hidden'});
                thePlayer.append('<div class="' + cssClass.time + ' ' + cssClass.timeCurrent + '"></div><div class="' + cssClass.bar + '"><div class="' + cssClass.barLoaded + '"></div><div class="' + cssClass.barPlayed + '"></div></div><div class="' + cssClass.time + ' ' + cssClass.timeDuration + '"></div><div class="' + cssClass.volume + '"><div class="' + cssClass.volumeButton + '" title="' + params.strVolume + '"><a href="#">' + params.strVolume + '</a></div><div class="' + cssClass.volumeAdjust + '"><div><div></div></div></div></div>');

                var theBar = thePlayer.find('.' + cssClass.bar),
                        barPlayed = thePlayer.find('.' + cssClass.barPlayed),
                        barLoaded = thePlayer.find('.' + cssClass.barLoaded),
                        timeCurrent = thePlayer.find('.' + cssClass.timeCurrent),
                        timeDuration = thePlayer.find('.' + cssClass.timeDuration),
                        volumeButton = thePlayer.find('.' + cssClass.volumeButton),
                        volumeAdjuster = thePlayer.find('.' + cssClass.volumeAdjust + ' > div'),
                        volumeDefault = 0,
                        adjustCurrentTime = function (e)
                        {
                            theRealEvent = isTouch ? e.originalEvent.touches[ 0 ] : e;
                            theAudio.currentTime = Math.round((theAudio.duration * (theRealEvent.pageX - theBar.offset().left)) / theBar.width());
                        },
                        adjustVolume = function (e)
                        {
                            theRealEvent = isTouch ? e.originalEvent.touches[ 0 ] : e;
                            theAudio.volume = Math.abs((theRealEvent.pageY - (volumeAdjuster.offset().top + volumeAdjuster.height())) / volumeAdjuster.height());
                        },
                        updateLoadBar = setInterval(function ()
                        {
                            barLoaded.width((theAudio.buffered.end(0) / theAudio.duration) * 100 + '%');
                            if (theAudio.buffered.end(0) >= theAudio.duration)
                                clearInterval(updateLoadBar);
                        }, 100);

                var volumeTestDefault = theAudio.volume, volumeTestValue = theAudio.volume = 0.111;
                if (Math.round(theAudio.volume * 1000) / 1000 == volumeTestValue)
                    theAudio.volume = volumeTestDefault;
                else
                    thePlayer.addClass(cssClass.noVolume);

                timeDuration.html('&hellip;');
                timeCurrent.text(secondsToTime(0));

                theAudio.addEventListener('loadeddata', function ()
                {
                    timeDuration.text(secondsToTime(theAudio.duration));
                    volumeAdjuster.find('div').height(theAudio.volume * 100 + '%');
                    volumeDefault = theAudio.volume;
                });

                theAudio.addEventListener('timeupdate', function ()
                {
                    timeCurrent.text(secondsToTime(theAudio.currentTime));
                    barPlayed.width((theAudio.currentTime / theAudio.duration) * 100 + '%');
                    if (theAudio.currentTime == 2)
                    {
                        console.log("add 1 view");
                    }
                });

                theAudio.addEventListener('volumechange', function ()
                {
                    volumeAdjuster.find('div').height(theAudio.volume * 100 + '%');
                    if (theAudio.volume > 0 && thePlayer.hasClass(cssClass.mute))
                        thePlayer.removeClass(cssClass.mute);
                    if (theAudio.volume <= 0 && !thePlayer.hasClass(cssClass.mute))
                        thePlayer.addClass(cssClass.mute);
                });

                theAudio.addEventListener('ended', function ()
                {
                    thePlayer.removeClass(cssClass.playing);
                    $("body").removeClass("playing");
                    if (playlist.length > 0 && sttPlay < playlist.length) {
                        var index = playlist.findIndex(x => x.id == sttBefore);
                        sttPlay = index + 1;
                        $("#songID").val(playlist[sttPlay].id);
                        sttBefore = playlist[sttPlay].id;
                        console.log("current index " + sttPlay);
                        var src = $("#url").val() + "/storage/audio/" + playlist[sttPlay].audio;
                        theAudio.currentSrc = src;
                        theAudio.src = src;
                        theAudio.load();
                        theAudio.play();
                        thePlayer.addClass(cssClass.playing);
                        $("body").addClass("playing");
                        $(".playlist-content-item").removeClass("active");
                        $("#pl" + playlist[sttPlay].id).addClass("active");
                        $.ajax({
                            url: $("#url").val() + "/player?do=listen",
                            cache: false,
                            type: 'get',
                            dataType: 'json',
                            data: {
                                id: playlist[sttPlay].id
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
                                    Swal.fire(
                                            'You do not have permission to play this song',
                                            'You have to buy this song before play it.',
                                            'error'
                                            );
                                } else if (result.msg == "success") {
                                    var thumbnail = $("#url").val() + "/storage/song/" + result.thumbnail;
                                    $('.player-control_left').find(".thumbnail-music").attr('src', thumbnail);
                                    $('.player-control_left').find(".media-title-name").html(result.name);
                                    $('.player-control_left').find(".media-title-singer").html(result.artistName);
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
                    }
                });

                theBar.on(eStart, function (e)
                {
                    adjustCurrentTime(e);
                    theBar.on(eMove, function (e) {
                        adjustCurrentTime(e);
                    });
                })
                        .on(eCancel, function ()
                        {
                            theBar.unbind(eMove);
                        });

                volumeButton.on('click', function ()
                {
                    if (thePlayer.hasClass(cssClass.mute))
                    {
                        thePlayer.removeClass(cssClass.mute);
                        theAudio.volume = volumeDefault;
                    } else
                    {
                        thePlayer.addClass(cssClass.mute);
                        volumeDefault = theAudio.volume;
                        theAudio.volume = 0;
                    }
                    return false;
                });

                volumeAdjuster.on(eStart, function (e)
                {
                    adjustVolume(e);
                    volumeAdjuster.on(eMove, function (e) {
                        adjustVolume(e);
                    });
                })
                        .on(eCancel, function ()
                        {
                            volumeAdjuster.unbind(eMove);
                        });
            } else
                thePlayer.addClass(cssClass.mini);

            if (isAutoPlay)
                thePlayer.addClass(cssClass.playing);

            thePlayer.find('.' + cssClass.playPause).on('click', function ()
            {
                if (thePlayer.hasClass(cssClass.playing))
                {
                    $("body").removeClass("playing");
                    $(this).attr('title', params.strPlay).find('a').html(params.strPlay);
                    thePlayer.removeClass(cssClass.playing);
                    isSupport ? theAudio.pause() : theAudio.Stop();
                } else
                {
                    $("body").addClass("playing");
                    $(this).attr('title', params.strPause).find('a').html(params.strPause);
                    thePlayer.addClass(cssClass.playing);
                    isSupport ? theAudio.play() : theAudio.Play();
                }
                return false;
            });

            $this.replaceWith(thePlayer);
        });
        return this;
    };
})(jQuery, window, document);