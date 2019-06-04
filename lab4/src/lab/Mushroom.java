package lab;

import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mushroom implements ActionListener {
    private float upperEyeLimit = 15.0f;
    private float lowerEyeLimit = 5.0f;
    private float farthestEyeLimit = 28.0f;
    private float nearestEyeLimit = 25.0f;

    private TransformGroup treeTransformGroup;
    private TransformGroup viewingTransformGroup;
    private Transform3D treeTransform3D = new Transform3D();
    private Transform3D viewingTransform = new Transform3D();
    private float angle = 0;
    private float eyeHeight;
    private float eyeDistance;
    private boolean descend = true;
    private boolean approaching = true;

    public static void main(String[] args) {
        new Mushroom();
    }

    private Mushroom() {
        Timer timer = new Timer(50, this);
        SimpleUniverse universe = new SimpleUniverse();

        viewingTransformGroup = universe.getViewingPlatform().getViewPlatformTransform();
        universe.addBranchGraph(createSceneGraph());

        eyeHeight = upperEyeLimit;
        eyeDistance = farthestEyeLimit;
        timer.start();
    }

    private BranchGroup createSceneGraph() {
        BranchGroup objRoot = new BranchGroup();

        treeTransformGroup = new TransformGroup();
        treeTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        buildMushroom();
        objRoot.addChild(treeTransformGroup);

        Background background = new Background(new Color3f(0.9f, 0.9f, 0.9f)); // white color
        BoundingSphere sphere = new BoundingSphere(new Point3d(0,0,0), 1000000000);
        background.setApplicationBounds(sphere);
        objRoot.addChild(background);

        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),100000.0);
        Color3f light1Color = new Color3f(1.0f, 1, 1);
        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        objRoot.addChild(light1);

        Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
        AmbientLight ambientLightNode = new AmbientLight(ambientColor);
        ambientLightNode.setInfluencingBounds(bounds);
        objRoot.addChild(ambientLightNode);
        return objRoot;
    }


    private TransformGroup buildTG() {
        return buildTG(new Vector3f(), new Transform3D());
    }

    private TransformGroup buildTG(Vector3f translation) {
        return buildTG(translation, new Transform3D());
    }

    private TransformGroup buildTG(Vector3f translation, Transform3D rotation){
        Transform3D transform = new Transform3D();
        TransformGroup transformG = new TransformGroup();
        transform.setTranslation(translation);
        transform.mul(rotation, transform);
        transformG.setTransform(transform);
        return transformG;
    }

    private TransformGroup buildDot(Vector3f transform){
        Sphere dot1 = new Sphere(0.6f, Utils.getFootAppearence());
        TransformGroup dot1TG = buildTG(transform);
        dot1TG.addChild(dot1);
        return dot1TG;
    }

    private void buildMushroom() {


        // foot
        Cylinder foot = new Cylinder(1, 14, Utils.getFootAppearence());
        Transform3D footT = new Transform3D();
        footT.rotX(Math.PI / 2);
        TransformGroup footTG = buildTG(new Vector3f(0, -3, 3), footT);
        footTG.addChild(foot);

        Cone footTop = new Cone(2, 5, Utils.getFootAppearence());
        TransformGroup footTopTG = buildTG(new Vector3f(0, 6.5f, 0));
        footTopTG.addChild(footTop);
        footTG.addChild(footTopTG);

        //head
        Cylinder head1 = new Cylinder(6, 1.5f, Utils.getBodyAppearence());
        TransformGroup head1TG = buildTG(new Vector3f(0, 9, 0));
        head1TG.addChild(head1);
        footTG.addChild(head1TG);

        Cylinder head2 = new Cylinder(5.9f, 0.5f, Utils.getFootAppearence());
        TransformGroup head2TG = buildTG(new Vector3f(0, 8, 0));
        head2TG.addChild(head2);
        footTG.addChild(head2TG);

        Cone headTop = new Cone(6, 3.5f, Utils.getBodyAppearence());
        TransformGroup headTopTG = buildTG(new Vector3f(0, 11.5f, 0));
        headTopTG.addChild(headTop);
        footTG.addChild(headTopTG);

        //dots
        footTG.addChild(buildDot(new Vector3f(1, 12, 2)));
        footTG.addChild(buildDot(new Vector3f(1, 12, 2)));
        footTG.addChild(buildDot(new Vector3f(-1, 12, 2)));
        footTG.addChild(buildDot(new Vector3f(-1, 12, -2)));
        footTG.addChild(buildDot(new Vector3f(0, 12, 2)));
        footTG.addChild(buildDot(new Vector3f(1, 12, -2)));
        footTG.addChild(buildDot(new Vector3f(1, 12, 3)));

        footTG.addChild(buildDot(new Vector3f(1, 11, 2)));
        footTG.addChild(buildDot(new Vector3f(1, 11, 2)));
        footTG.addChild(buildDot(new Vector3f(-3, 11, 2)));
        footTG.addChild(buildDot(new Vector3f(-3, 11, -2)));
        footTG.addChild(buildDot(new Vector3f(-4, 11, 2)));
        footTG.addChild(buildDot(new Vector3f(0, 11, -2)));
        footTG.addChild(buildDot(new Vector3f(0, 11, 3)));

        footTG.addChild(buildDot(new Vector3f(4, 11, 0)));
        footTG.addChild(buildDot(new Vector3f(4, 11, -2)));
        footTG.addChild(buildDot(new Vector3f(4, 11, 2)));
        footTG.addChild(buildDot(new Vector3f(0, 11, -5)));
        footTG.addChild(buildDot(new Vector3f(0, 11, 4)));
        footTG.addChild(buildDot(new Vector3f(1, 11, -3)));
        footTG.addChild(buildDot(new Vector3f(1, 11, -3)));


        treeTransformGroup.addChild(footTG);

    }

    // ActionListener interface
    @Override
    public void actionPerformed(ActionEvent e) {
        float delta = 0.03f;

        // rotation of the castle
        treeTransform3D.rotZ(angle);
        treeTransformGroup.setTransform(treeTransform3D);
        angle += delta;

        // change of the camera position up and down within defined limits
        if (eyeHeight > upperEyeLimit){
            descend = true;
        }else if(eyeHeight < lowerEyeLimit){
            descend = false;
        }
        if (descend){
            eyeHeight -= delta;
        }else{
            eyeHeight += delta;
        }

        // change camera distance to the scene
        if (eyeDistance > farthestEyeLimit){
            approaching = true;
        }else if(eyeDistance < nearestEyeLimit){
            approaching = false;
        }
        if (approaching){
            eyeDistance -= delta;
        }else{
            eyeDistance += delta;
        }

        Point3d eye = new Point3d(eyeDistance, eyeDistance, eyeHeight); // spectator's eye
        Point3d center = new Point3d(.0f, .0f ,0.1f); // sight target
        Vector3d up = new Vector3d(.0f, .0f, 1.0f);; // the camera frustum
        viewingTransform.lookAt(eye, center, up);
        viewingTransform.invert();
        viewingTransformGroup.setTransform(viewingTransform);
    }
}
