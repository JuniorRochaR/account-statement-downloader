package com.ebanx.lambda.actions;

import javax.enterprise.context.ApplicationScoped;

import com.ebanx.lambda.exception.AccountStatementApiException;
import com.ebanx.lambda.internal.Command;
import com.ebanx.lambda.utils.IdTokenizerUtils;

@ApplicationScoped
public class RetrievePayeeIdAction implements Command<DownloaderRequest, DownloaderResponse> {

    private static final String DIGITAL_ACCOUNT_PREFIX = "dac_";

    @Override
    public void execute(DownloaderRequest request, DownloaderResponse response) {
        Long payeeId = getPayeeId(request.getDigitalAccountId());
        response.setPayeeId(payeeId);
    }

    private long getPayeeId(String payeeId) {
        try {
            return payeeId.contains(DIGITAL_ACCOUNT_PREFIX) ? IdTokenizerUtils.fromToken(payeeId) : Long.parseLong(payeeId);
        } catch (Exception e) {
            throw new AccountStatementApiException("The payee id passed is invalid", e);
        }
    }
}
