package com.ebanx.lambda.actions;

import java.io.InputStream;

public class DownloaderResponse {

    private static final String FILE_EXTENSION = ".csv";

    private Long payeeId;
    private String dateBucketPath;
    private InputStream statementCsv;

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

    public InputStream getStatementCsv() {
        return statementCsv;
    }

    public void setStatementCsv(InputStream statementCsv) {
        this.statementCsv = statementCsv;
    }

    public String getFileName() {
        return dateBucketPath + payeeId + FILE_EXTENSION;
    }
}
