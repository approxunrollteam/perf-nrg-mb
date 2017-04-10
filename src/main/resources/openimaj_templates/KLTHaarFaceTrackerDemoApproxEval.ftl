
/**
* Copyright (c) 2011, The University of Southampton and the individual contributors.
* All rights reserved.
* <p>
    * Redistribution and use in source and binary forms, with or without modification,
    * are permitted provided that the following conditions are met:
    * <p>
    * * Redistributions of source code must retain the above copyright notice,
    * this list of conditions and the following disclaimer.
    * <p>
    * * Redistributions in binary form must reproduce the above copyright notice,
    * this list of conditions and the following disclaimer in the documentation
    * and/or other materials provided with the distribution.
    * <p>
    * * Neither the name of the University of Southampton nor the names of its
    * contributors may be used to endorse or promote products derived from this
    * software without specific prior written permission.
    * <p>
    * <p>
    * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
    * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
    * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
    * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
    * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
    * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
    * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
    * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
    * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
    * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
    */
    package org.openimaj.approxdemos;

    import org.openimaj.image.MBFImage;
    import org.openimaj.image.colour.RGBColour;
    import org.openimaj.image.processing.face.detection.DetectedFace;
    import org.openimaj.image.processing.face.tracking.KLTHaarFaceTracker;
    import org.openimaj.math.geometry.shape.Rectangle;
    import org.openimaj.video.VideoDisplay;
    import org.openimaj.video.VideoDisplayListener;
    import org.openimaj.video.VideoDisplayStateListener;
    import org.openimaj.video.xuggle.XuggleVideo;

    import javax.swing.*;
    import java.io.BufferedWriter;
    import java.io.File;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.net.MalformedURLException;
    import java.util.List;

    /**
    * A demo of the KLT/HaarCascade face tracker
    *
    * @author David Dupplaw (dpd@ecs.soton.ac.uk)
    * @created 13 Oct 2011
    */
    public class KLTHaarFaceTrackerDemoApproxEval {
    /**
    * The face tracker
    */
    private KLTHaarFaceTracker faceTracker;// = new KLTHaarFaceTracker(40);

    /**
    * The video with faces in to track
    */
    private XuggleVideo video = null;

    volatile int frameCounter = 0;

    private void writeln(BufferedWriter out2) {
    try {
    out2.write("\n");
    } catch (IOException e) {
    e.printStackTrace();
    }
    }

    private void writeRect(BufferedWriter out2, Rectangle r) {
    try {
    System.out.println("Video:" + video.getCurrentFrameIndex());
    out2.write(String.valueOf(video.getCurrentFrameIndex()));
    //out2.write(String.valueOf(frameCounter));
    out2.write(";");
    out2.write(String.valueOf(r.x));
    out2.write(";");
    out2.write(String.valueOf(r.y));
    out2.write(";");
    out2.write(String.valueOf(r.width));
    out2.write(";");
    out2.write(String.valueOf(r.height));
    out2.write(";");
    } catch (IOException e) {
    e.printStackTrace();
    }
    }

    /**
    * Default contructor
    *
    * @throws MalformedURLException
    */
    public void eval(String outPath, String inputPath, String name) throws IOException, InterruptedException {

    String fileName = inputPath.substring(inputPath.lastIndexOf("/") + 1);
    fileName = outPath + "/" + name + "_" + fileName + ".txt";

    final BufferedWriter out2 = new BufferedWriter(new FileWriter(fileName), 2048 * 2);

    // Load the video
    /*URL url = oprg.openimaj.approxdemos.KLTHaarFaceTrackerDemoApproxEval.class.getResource(inputPath);
    if (url == null) {
    url = getAFile().toURI().toURL();
    }*/
    final KLTHaarFaceTrackerDemoApproxEval runner = this;

    video = new XuggleVideo(inputPath);
    faceTracker = new KLTHaarFaceTracker(40);

    // Jump into the video to a place where there are faces.
    video.setCurrentFrameIndex(10);


    frameCounter = 0;

    final VideoDisplay
    <MBFImage> vd = VideoDisplay.createVideoDisplay(video);
        vd.addVideoDisplayStateListener(new VideoDisplayStateListener() {
        @Override
        public void videoStopped(VideoDisplay<?> v) {
        frameCounter = 500;
        try {
        out2.close();
        } catch (IOException e) {
        e.printStackTrace();
        }
        synchronized (runner) {
        runner.notify();
        }
        }

        @Override
        public void videoPlaying(VideoDisplay<?> v) {

        }

        @Override
        public void videoPaused(VideoDisplay<?> v) {

        }

        @Override
        public void videoStateChanged(VideoDisplay.Mode mode, VideoDisplay<?> v) {

        }
        });
        vd.addVideoListener(new VideoDisplayListener
        <MBFImage>() {
            @Override
            public void beforeUpdate(MBFImage frame) {

            // Pass the image to our face tracker
            List
            <DetectedFace> faces = faceTracker.trackFace(frame.flatten());
                //System.out.println("Frame: " + (frameCounter++) + ", " + faces.size() + " faces ");
                frameCounter++;
                if (frameCounter >= 10 && frameCounter % 10 == 0) {
                for (DetectedFace face : faces) {
                Rectangle r = face.getBounds();
                writeRect(out2, r);
                }
                if (faces.size() > 0) writeln(out2);
                } else {
                for (DetectedFace face : faces) {
                Rectangle r = face.getBounds();
                frame.drawShape(r, RGBColour.RED);
                }
                }
                if (frameCounter > 500) {
                /*
                try {
                out2.close();
                } catch (IOException e) {
                e.printStackTrace();
                }
                synchronized (runner) {
                runner.notify();
                }*/
                }
                }

                @Override
                public void afterUpdate(VideoDisplay
                <MBFImage> display) {
                    }
                    });
                    synchronized (this) {
                    while (frameCounter < 500) wait();
                    }
                    frameCounter = 0;
                    video.reset();
                    video.close();
                    video = null;
                    vd.close();
                    }

                    private File getAFile() {
                    JFileChooser c = new JFileChooser();
                    // Demonstrate "Open" dialog:
                    int rVal = c.showOpenDialog(new JFrame());
                    if (rVal == JFileChooser.APPROVE_OPTION) {
                    return c.getSelectedFile();
                    }
                    return null;
                    }

                    /**
                    * Default main
                    *
                    * @param args Command-line arguments
                    * @throws MalformedURLException If the URL for the video isn't right
                    */
                    public static void main(String[] args) throws IOException, InterruptedException {
                    new KLTHaarFaceTrackerDemoApproxEval().run();
                    }
                    public void run() throws IOException, InterruptedException {
                    String name = "${strategy}.${loop_uid}";//WTF
                    eval("/home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/OUTPUT/OpenIMaJ",
                    "/home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_DATA/FACE_VIDEOS/guy_goma.mp4", name);

                    eval("/home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/OUTPUT/OpenIMaJ",
                    "/home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_DATA/FACE_VIDEOS/obama360.mp4", name);

                    eval("/home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/OUTPUT/OpenIMaJ",
                    "/home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_DATA/FACE_VIDEOS/merkel360.mp4", name);

                    eval("/home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/OUTPUT/OpenIMaJ",
                    "/home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_DATA/FACE_VIDEOS/ParkShinHye360.mp4", name);

                    eval("/home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/OUTPUT/OpenIMaJ",
                    "/home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_DATA/FACE_VIDEOS/Kelly_McGonigal.mp4", name);

                    System.exit(0);
                    }
                    }

