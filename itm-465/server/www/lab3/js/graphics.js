(function (lab3, $, undefined) {

    lab3.init = function (hook) {
        // section 1 //////////////////////

        // See the README.markdown file for written answers to steps 4 and 6

        var WIDTH = 600,
            HEIGHT = 500,
            ASPECT = WIDTH / HEIGHT,
            CAMERA_FOV = 65,
            CAMERA_NEAR = 0.1,
            CAMERA_FAR = 10000,
            camera = new THREE.PerspectiveCamera(CAMERA_FOV, ASPECT, CAMERA_NEAR, CAMERA_FAR),
            renderer = new THREE.WebGLRenderer({antialias: true}),
            scene = new THREE.Scene(),
            pointLight = new THREE.PointLight(0xFFFFFF),
            pointLightPositionVector = new THREE.Vector3(-10, 100, 100),
            cubeMaterial = new THREE.MeshLambertMaterial({ color: 0x00BBCC }),
            cubeGeometry = new THREE.CubeGeometry(40, 55, 30),
            cubeMesh = new THREE.Mesh(cubeGeometry, cubeMaterial),
            renderLoop = function () {
                renderer.render(scene, camera);
                window.requestAnimationFrame(renderLoop);
                controls.update();
            };
        renderer.setSize(WIDTH, HEIGHT);
        camera.position.z = 300;
        pointLight.position = pointLightPositionVector;
        scene.add(pointLight);
        scene.add(camera);
        scene.add(cubeMesh);
        hook.append(renderer.domElement);
        window.requestAnimationFrame(renderLoop);

        // section 2 //////////////////////

        var controls = new THREE.TrackballControls(camera);
        controls.target.set(0, 0, 0);

        // section 3 //////////////////////

        var ambientLight = new THREE.AmbientLight(0x555555),
            teapot = null,
            teapotMaterial = new THREE.MeshLambertMaterial({ color: 0x0EEE00, side: THREE.DoubleSide }),
            loader = new THREE.JSONLoader();
        scene.add(ambientLight);
        loader.load("models/utah-teapot.json", function(geometry) {
            teapot = new THREE.Mesh (geometry, teapotMaterial);
            teapot.position.z = 0;
            teapot.position.x = 0;
            teapot.position.y = 28;
            teapot.scale.set(4,5,5);
            scene.add(teapot);
        });

        // section 4 //////////////////////

        var teapotHeight = 0,
            updateTeaPot = function () {
                if (teapot != null) {
                    teapotHeight+=1;
                    teapot.position.y = teapotHeight%200;
                    teapot.rotation.z = teapotHeight / 18;
                }
            },
            renderLoop = function () {
                renderer.render(scene, camera);
                window.requestAnimationFrame(renderLoop);
                controls.update();
                updateTeaPot();
            };

    }


})(window.lab3 = window.lab3 || {}, jQuery)