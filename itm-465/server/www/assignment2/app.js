isStorageSupported = function () {
    try {
        return 'localStorage' in window && window.localStorage !== null;
    } catch (e) {
        return false;
    }
};

(function (app, $, undefined) {

    $(document).ready(function () {

        var player = $('#playerStream'),
            playerStream = $('#playerStream')[0],
            playerStartButton = $('#playerStartButton'),
            playerPauseButton = $('#playerPauseButton'),
            playerProgressBar = $('#playerProgressBar'),
            playerFullScreenButton = $('#playerFullScreenButton'),
            playerDefaultScreenButton = $('#playerDefaultScreenButton'),
            playerStreamDescription = $('#playerStreamDescription'),
            playerLoadingGif = $('#playerLoadingGif'),
            playerStreamName = $('#playerStreamName'),
            playerStreamDescriptionVisible = false,
            clipCarousel = $('#clipCarousel'),
            clips = null,
            playerHistory = isStorageSupported() && localStorage.getItem("player-history") ? JSON.parse(localStorage.getItem("player-history")) : [];

        // Add Input Event handlers

        app.playerStreamProgressChange = function () {
            var percentage = Math.floor((100 / playerStream.duration) *
                    playerStream.currentTime),
                cssWidthValue = percentage + '%',
                titleValue = Math.floor(playerStream.currentTime) + ' of ' + Math.floor(playerStream.duration) + ' seconds played';
            playerProgressBar.css('width', cssWidthValue);
            playerProgressBar.attr('title', titleValue);

            if (percentage > 25 && !playerStreamDescriptionVisible) {
                playerStreamDescriptionVisible = true;
                playerStreamDescription.css('margin-right', '100%');
                playerStreamDescription.removeClass('hidden');
                playerStreamDescription.addClass("enter-stage-left");
            }
        };
        app.playerStreamEnded = function () {
            app.playerStreamReset();
        };
        app.playerStreamPlay = function () {
            playerStream.play();
            playerStartButton.addClass('hidden');
            playerPauseButton.removeClass('hidden');
        };
        app.playerStreamPause = function () {
            playerStream.pause();
            playerStartButton.removeClass('hidden');
            playerPauseButton.addClass('hidden');
        };
        app.playerStreamMaximize = function () {

            if (playerStream.requestFullScreen) {
                //w3c
                playerStream.requestFullScreen();
            } else if (playerStream.webkitRequestFullScreen) {
                //Google Chrome
                playerStream.webkitRequestFullScreen(playerStream.ALLOW_KEYBOARD_INPUT);
            } else if (playerStream.mozRequestFullScreen) {
                //Firefox
                playerStream.mozRequestFullScreen();
            } else {
                alert('Your browser does not Support Full Screen Mode.');
                return;
            }
        };
        app.playerStreamReset = function () {
            playerStream.pause();
            playerStartButton.removeClass('hidden');
            playerPauseButton.addClass('hidden');
            playerStreamDescription.addClass('hidden');
            playerStreamDescription.removeClass('enter-stage-left');
            playerStreamDescriptionVisible = false;
            playerStream.currentTime = 0;
            playerProgressBar.css('width', '0%');
            playerProgressBar.attr('title', +'0 of ' + Math.floor(playerStream.duration) + ' seconds played');
        };
        app.playerStreamLoad = function (caller, url, title, poster) {
            var streamDescriptionHtml = '<h3>' + unescape(title) + '</h3>',
                streamNameHtml = '<h4>' + caller.title + '</h4>',
                clipId = caller.id;
            console.log('clipId = ' + clipId);
            app.playerStreamReset();
            app.showPlayer(false);
            playerStreamName.html(streamNameHtml);
            playerStream.src = url;
            playerStreamDescription.html(streamDescriptionHtml);
            playerStream.title = caller.title;
            playerStream.poster = poster;
            app.showPlayer();
            app.updateClipHistory(clipId);
        };

        // Add video stream listeners

        playerStream.addEventListener('timeupdate', app.playerStreamProgressChange, false);
        playerStream.addEventListener('ended', app.playerStreamEnded, false);

        // Add clip load logic

        $(document).ajaxComplete(function (event, xhr, settings) {
            if (settings.url === "clips.json") {
                clips = app.orderClips(JSON.parse(xhr.responseText));
                console.log(clips);
                app.loadCarousel(clips);
                app.startPlayer(clips[0]);
            }
        });
        app.orderClips = function (rawClips) {
            var playedClips = [],
                unplayedClips = [],
                sortedClips = [],
                i = 0,
                clip = null,
                clonedClip = null;

            if (!isStorageSupported()) {
                return rawClips;
            }

            for (i = 0; i < rawClips.length; i++) {
                clip = rawClips[i];
                clonedClip = app.cloneClip(rawClips[i]);
                if ($.inArray(clip.id, playerHistory) >= 0) {
                    playedClips.push(clonedClip);
                } else {
                    unplayedClips.push(clonedClip);
                }
            }
            /* console.log('unplayedClips');
             console.log(unplayedClips);
             console.log('playedClips');
             console.log(playedClips); */

            if (unplayedClips.length > 0) {
                $.merge(sortedClips, unplayedClips);
            }
            if (playedClips.length > 0) {
                $.merge(sortedClips, playedClips);
            }

            console.log('sortedClips');
            console.log(sortedClips);

            return sortedClips;

        };
        app.startPlayer = function (clip) {
            var streamDescriptionHtml = '<h3>' + unescape(clip.description) + '</h3>',
                streamNameHtml = '<h4>' + clip.name + '</h4>';
            playerStream.src = clip ['content-url'];
            playerStream.poster = clip ['thumb-url'];
            playerStreamDescription.html(streamDescriptionHtml);
            playerStreamName.html(streamNameHtml);
            playerStreamName.removeClass("hidden");

            app.showPlayer();
        };
        app.loadCarousel = function (clips) {
            var i = 0,
                openItemHtml = '<div class="item"><ul class="thumbnails">',
                openActiveItemHtml = '<div class="item active"><ul class="thumbnails">',
                closeItemHtml = '</ul></div>',
                innerClipCarouselHtml = openActiveItemHtml,
                clip = null,
                itemSize = 3;

            for (i = 0; i < clips.length; i++) {
                clip = clips[i];
                // <li class="span3">
                innerClipCarouselHtml += '<li class="span3">';
                // <div class="thumbnail" onclick="app.playerStreamLoad(this, clip[content-url], clip.description, clip[thumb-url]);" title="War on Drugs continues">
                innerClipCarouselHtml += '<div id="' + clip.id + '" class="thumbnail" ';
                innerClipCarouselHtml += 'onclick="app.playerStreamLoad(this, ';
                innerClipCarouselHtml += "'" + clip['content-url'] + "', ";
                innerClipCarouselHtml += "'" + escape(clip.description) + "', ";
                innerClipCarouselHtml += "'" + clip['thumb-url'] + "');";
                innerClipCarouselHtml += '" title="' + clip.name + '">';
                // <img src="http://buffalogrove.sat.iit.edu/thumb/dogs_friends-t2.jpg">
                innerClipCarouselHtml += '<img src="' + clip['thumb-url'] + '">';
                // <a class="center thumbnail-play">›</a>
                innerClipCarouselHtml += '<a class="center thumbnail-play">›</a>';
                // </div>
                innerClipCarouselHtml += '</div>';
                innerClipCarouselHtml += '</li>';

                if (i == itemSize) {
                    innerClipCarouselHtml += closeItemHtml + openItemHtml;
                }
            }
            clipCarousel.html(innerClipCarouselHtml);
        };
        $(document).load("clips.json");

        // Utilities

        app.cloneClip = function (clip) {
            return {
                "id": clip.id,
                "name": clip.name,
                "description": clip.description,
                "content-url": clip['content-url'],
                "thumb-url": clip['thumb-url']
            };
        };
        app.showPlayer = function (hide) {
            if (!!hide) {
                playerLoadingGif.removeClass('hidden');
                player.addClass('hidden');
            } else {
                player.removeClass('hidden');
                playerLoadingGif.addClass('hidden');

            }
        };
        app.updateLocalStorage = function () {
            window.incrementDataStore = localStorage.setItem("player-history", JSON.stringify(playerHistory));
        };
        app.updateClipHistory = function (id) {
            if (playerHistory.length == 0 || $.inArray(id, playerHistory) < 0) {
                console.log('id = ' + id);
                playerHistory.push(id);
                app.updateLocalStorage();
            }
        };

    });


})(window.app = window.app || {}, jQuery)