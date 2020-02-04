'use strict'

+function (global,undefined) {
    var vendors=['webkit','moz','ms','o'];

    global.cancelAnimationFrame = global.cancelAnimationFrame || global.cancelRequestAnimationFrame;

    for (var i=0; !global.requestAnimationFrame && i<vendors.length ; i++ ){
        global.requestAnimationFrame=global[vendors[i]+'RequestAnimationFrame'];
        global.cancelAnimationFrame=global[vendors[i]+'CancelAnimationFrame'] || global[vendors[i]+'CancelRequestAnimationFrame'];
    }

    if (!global.requestAnimationFrame){
        var lastTime=0;

        global.requestAnimationFrame=function (callback,refreshRate) {
            var currentTime=(new Date()).getTime();

            var deltaTime=Math.max(0,16.67-(currentTime-lastTime));

            lastTime=currentTime+deltaTime;

            return setTimeout(function () {
                callback && callback(lastTime);
            },deltaTime);
        };

        global.cancelAnimationFrame=function (id) {
            clearTimeout(id);
        };
    }
}(window || this);
