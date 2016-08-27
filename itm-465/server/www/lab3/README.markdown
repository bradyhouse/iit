LAB3
======

### Section 1

#### Step 4

Check to see if you can see anything on screen, check the javascript console for errors -- what might be missing?

* The canvas appears blank because the renderer.render method has not been called.
* Additionally, there was a mistake in the lab instructions.  If you use the [THREE.BoxGeometry](, which
isn't available in the R61 version of the three.js library, it won't work.  Instead (after nearly 2 hours of trial and error debugging), 
you need to use the constructor "THREE.CubeGeometry" constructor. This works with the R61 version.  Of course, it doesn't work 
with the R71 edition, which is the latest and greatest.

### Step 6

Some thing to consider - is the box the color it is because the material blue or the light is blue?

* The box (or cube) is blue because of the material, which is blue.  The light added to the scene is white.


### Grade

49/50

### Feedback

N/A


