var renderer = null,
    scene = null,
    camera = null,
    cube = null,
    animating = true,
    mapUrl = "content/images/my-iit-screenscrape.png";

function onLoad() {
    var container = document.getElementById("blingContainer");
    renderer = new THREE.WebGLRenderer( { antialias: true } );
    renderer.setSize(container.offsetWidth, container.offsetHeight);
    container.appendChild( renderer.domElement );
    scene = new THREE.Scene();
    camera = new THREE.PerspectiveCamera( 45, container.offsetWidth / container.offsetHeight, 1, 4000 );
    camera.position.set( 0, 0, 2 );
    var light = new THREE.DirectionalLight( 0xCC9933, 1.5);
    light.position.set(0, 0, 1);
    scene.add( light );
    var map = THREE.ImageUtils.loadTexture(mapUrl);
    var material = new THREE.MeshPhongMaterial({ map: map });
    var geometry = new THREE.CubeGeometry(1, 1, 1);
    cube = new THREE.Mesh(geometry, material);
    cube.rotation.x = Math.PI / 5;
    cube.rotation.y = Math.PI / 5;
    scene.add( cube );
    addMouseHandler();
    run();
}

function run() {
    renderer.render( scene, camera );
    if (animating) {
        cube.rotation.x -= 0.01;
        cube.rotation.y = 0.01;
    }
    requestAnimationFrame(run);
}

function addMouseHandler() {
    var dom = renderer.domElement;
    dom.addEventListener( 'mouseup', onMouseUp, false);
}

function onMouseUp	(event) {
    event.preventDefault();
    animating = !animating;
}

/**
 * Provides requestAnimationFrame in a cross browser way.
 * http://paulirish.com/2011/requestanimationframe-for-smart-animating/
 */

if ( !window.requestAnimationFrame ) {
    window.requestAnimationFrame = ( function() {
        return window.webkitRequestAnimationFrame ||
        window.mozRequestAnimationFrame ||
        window.oRequestAnimationFrame ||
        window.msRequestAnimationFrame ||
        function(callback, element ) {
            window.setTimeout( callback, 1000 / 60 );
        };
    } )();
}