package com.hedera.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hedera.sdk.common.HederaPrecheckResult;
import com.hedera.sdk.file.HederaFile;

public final class FileGetContents {
	public static boolean getContents(HederaFile file) throws Exception {
		final Logger logger = LoggerFactory.getLogger(FileGetContents.class);
		
		logger.info("");
		logger.info("FILE GET CONTENTS");
		logger.info("");
		
		// run a get contents query
		byte[] contents = file.getContents();
		if (contents != null) {
			// it was successful, print it
			logger.info("===>Got contents=");
			logger.info(new String(contents,"UTF-8"));
			return true;
		} else if (file.getPrecheckResult() == HederaPrecheckResult.BUSY) {
			logger.info("system busy, try again later");
			return false;
		} else {
			// an error occurred
			logger.info("===>Getting contents - precheck ERROR" + file.getPrecheckResult());
			return false;
		}
	}

}
