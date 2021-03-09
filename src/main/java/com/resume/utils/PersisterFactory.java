package com.resume.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PersisterFactory<T> implements Serializable {

	private static final File f = new File("./backup.ser");
	
	private class BackupObject<T> implements Serializable {
		private static final long serialVersionUID = 1L;
		List<T> tList = new ArrayList<>();

		public BackupObject() {

		}

	}

	public void backup(T t) {
		BackupObject<T> bo = (BackupObject<T>) read();
		if (bo == null) {
			bo = new BackupObject<T>();
		}
		Iterator<T> it = bo.tList.iterator();
		while (it.hasNext()) {
			T elm = it.next();
			if (elm.equals(t))
				it.remove();
		}
		bo.tList.add(t);
		write(bo);
	}

	public List<T> restore() {
		BackupObject<T> bo = read();
		if (bo == null)
			bo = new BackupObject<T>();
		return bo.tList;
	}

	private BackupObject<T> read() {
		BackupObject<T> bo = null;
		try {
			FileInputStream file = new FileInputStream(f);
			ObjectInputStream in = new ObjectInputStream(file);

			bo = (BackupObject<T>) in.readObject();

			in.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bo;
	}

	private void write(BackupObject<T> bo) {
		try {
			FileOutputStream fo = new FileOutputStream(f);
			ObjectOutputStream out = new ObjectOutputStream(fo);
			out.writeObject(bo);
			fo.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
