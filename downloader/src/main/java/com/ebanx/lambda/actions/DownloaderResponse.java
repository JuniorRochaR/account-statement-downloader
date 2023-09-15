package com.ebanx.lambda.actions;

import java.io.InputStream;

public class DownloaderResponse {

    private static final String FILE_EXTENSION = ".zip";

    private Long payeeId;
    private String dateBucketPath;
    private String statementCsv;

    public Long getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
    }

    public String getDateBucketPath() {
        return dateBucketPath;
    }

    public void setDateBucketPath(String dateBucketPath) {
        this.dateBucketPath = dateBucketPath;
    }

    public String getStatementCsv() {
        return statementCsv;
    }

    public void setStatementCsv(String statementCsv) {
        this.statementCsv = statementCsv;
    }

    public String getFileName() {
        return dateBucketPath + payeeId + FILE_EXTENSION;
    }
}
