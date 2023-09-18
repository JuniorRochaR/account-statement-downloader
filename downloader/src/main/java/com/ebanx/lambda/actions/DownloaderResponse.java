package com.ebanx.lambda.actions;

public class DownloaderResponse {

    private static final String FILE_EXTENSION = ".zip";

    private Long payeeId;
    private String dateBucketPath;
    private String fileUrl;

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

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return dateBucketPath + payeeId + FILE_EXTENSION;
    }
}
