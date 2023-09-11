package com.ebanx.lambda.actions;

import javax.enterprise.context.ApplicationScoped;

import com.ebanx.lambda.internal.Command;
import com.ebanx.lambda.utils.DateUtils;

@ApplicationScoped
public class ConvertDateAction implements Command<DownloaderRequest, DownloaderResponse> {

    @Override
    public void execute(DownloaderRequest request, DownloaderResponse response) {
        response.setDateBucketPath(DateUtils.dateToBucketPath(request.getDate()));
    }
}
