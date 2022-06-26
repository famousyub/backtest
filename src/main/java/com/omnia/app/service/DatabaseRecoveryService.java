package com.omnia.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.omnia.app.exception.ResourceNotFoundException;

@Service
public class DatabaseRecoveryService {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseRecoveryService.class);

	// windows conf
	/*
	 * String directory = "C:/Users/hasse/Desktop/OMNIA_REPO"; String mysqldumpCmd =
	 * "C:/wamp64/bin/mysql/mysql5.7.31/bin/mysqldump"; String mysqlCmd =
	 * "C:/wamp64/bin/mysql/mysql5.7.31/bin/mysql";
	 */
	// linux conf

	String directory = "/var/backend/OMNIA_REPO";
	String mysqldumpCmd = "mysqldump";
	String mysqlCmd = "mysql";

	@Value("spring.datasource.db-name")
	String dbName;// = "omnia_app";

	@Value("spring.datasource.username")
	String dbUser;// = "root";

	@Value("spring.datasource.password")
	String dbPass;// = "administrator";

	public boolean saveDatabaseRecovery() {
		logger.info("SR_DatabaseRecoveryService_FN_saveDB");

		boolean savedDb = saveDbCommand(this.dbUser, this.dbPass, this.dbName);

		return savedDb;
	}

	public void restoreDatabaseRecovery(String dbRecoveryName) {
		String[] executeCmd = new String[] { mysqlCmd, "--user=" + dbUser, "--password=" + dbPass, dbName, "-e",
				" source " + directory + "/" + dbRecoveryName };
		Process runtimeProcess;

		try {
			runtimeProcess = Runtime.getRuntime().exec(executeCmd);
			int processComplete = runtimeProcess.waitFor();
			if (processComplete == 0) {
				System.out.println("Backup restored successfully");
			} else {
				System.out.println("Backup not restored");
			}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<String> getAllDbRecoveries() {
		logger.info("SR_DatabaseRecoveryService_FN_getAllDbRecoveries");

		Path dir = Paths.get(directory);
		List<String> result = new ArrayList<String>();

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
			for (Path file : stream) {
				System.out.println(file.getFileName());
				result.add("" + file.getFileName());//
			}

		} catch (IOException | DirectoryIteratorException x) {
			// IOException can never be thrown by the iteration.
			// In this snippet, it can only be thrown by newDirectoryStream.
			System.err.println(x);
		}
		return result;
	}

	public void deleteDatabaseRecovery(String dbRecoveryName) {
		logger.info("SR_DatabaseRecoveryService_FN_deleteDatabaseRecovery");

		try {
			if (!deleteDBFromServer(dbRecoveryName)) {
				throw new ResourceNotFoundException("DatabaseRecovery", "server file", dbRecoveryName);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean deleteDBFromServer(String fileName) throws IOException, InterruptedException {

		File file = new File(directory + "/" + fileName);
		boolean fileDeleted = file.delete();
		return fileDeleted;
	}

	public String getDateTimeOfToday() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	public Boolean saveDbCommand(String dbUser, String dbPass, String dbName) {

		String outputFile = /* dbName + "_" + */ getDateTimeOfToday() + "_.sql";
		String executeCmd = "";
		boolean savedDb = false;
		try {
			executeCmd = mysqldumpCmd + " -u " + dbUser + " -p" + dbPass + " " + dbName + " -r " + directory + "/"
					+ outputFile;
			Process runtimeProcess;
			System.out.println(executeCmd);
			runtimeProcess = Runtime.getRuntime().exec(executeCmd);
			int processComplete = runtimeProcess.waitFor();
			if (processComplete == 0) {
				System.out.println("Backup taken successfully");
				savedDb = true;
			}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return savedDb;
	}
}
