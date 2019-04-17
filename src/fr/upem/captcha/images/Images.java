package fr.upem.captcha.images;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;

public abstract class Images implements Database {
	private ArrayList<File> fileList;
	
	protected Images() {
		fileList = new ArrayList<File>();
		addAllFiles();
	}
	
	private File getCurrentClassDir() {
		String className = this.getClass().getSimpleName() + ".class";
		URL url = this.getClass().getResource(className);
		String uri = url.getFile().replace("/" + className, "");
		return new File(uri);
	}
	
	private void addAllFiles() {

		File curDir = getCurrentClassDir();
		final String[] EXTENSIONS = new String[]{ "jpeg", "png", "jpg" };
		final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
	        @Override
	        public boolean accept(final File dir, final String name) {
	            for (final String ext : EXTENSIONS) {
	                if (name.endsWith("." + ext)) {
	                    return (true);
	                }
	            }
	            return (false);
	        }
	    };
        for (final File f : curDir.listFiles(IMAGE_FILTER)) {
        	if (f != null) {
        		fileList.add(f);
        	}
        	/*
        	BufferedImage img = null;
        	try {
                img = ImageIO.read(f);

                // you probably want something more involved here
                // to display in your UI
                System.out.println("image: " + f.getName());
                System.out.println(" width : " + img.getWidth());
                System.out.println(" height: " + img.getHeight());
                System.out.println(" size  : " + f.length());
            } catch (final IOException e) {
                // handle errors here
            }
            */
        }
        
	}	
	
	public List<File> getPhotos() {
		return Collections.unmodifiableList(this.fileList);		
	};	
	public List<File> getRandomPhotosURL(int amount) {
		Collections.shuffle(this.fileList);
		int sub = Math.min(amount, this.fileList.size());
		return Collections.unmodifiableList(this.fileList.subList(0, sub));
	};
	public File getRandomPhotoURL() {
		Collections.shuffle(this.fileList);
		return this.fileList.get(0);
	};
	public int amount() {
		return this.fileList.size();		
	};
}
