package chapter22;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.currentThread;

public class Document {
	private boolean changed = false;
	
	private List<String> context = new ArrayList<>();
	
	private final FileWriter writer;
	
	private static AutoSaveThread autoSaveThread;
	
	private Document(String path, String name) throws IOException {
		this.writer = new FileWriter(new File(path, name), true);
	}
	
	public static Document create(String path, String name) throws IOException {
		Document doc = new Document(path, name);
		autoSaveThread = new AutoSaveThread(doc);
		autoSaveThread.start();
		return doc;
	}
	
	public void edit(String context) {
		synchronized (this) {
			this.context.add(context);
			this.changed = true;
		}
	}
	
	public void close() throws IOException {
		autoSaveThread.interrupt();
		writer.close();
	}
	
	public void save() throws IOException {
		synchronized (this) {
			if (!changed) {
				return ;
			}
			
			System.out.println(currentThread() + " execute the save action");
			
			for (String string : context) {
				this.writer.write(string);
				this.writer.write("\r\n");
			}
			
			this.writer.flush();
			this.changed = false;
			this.context.clear();
		}
	}
}
