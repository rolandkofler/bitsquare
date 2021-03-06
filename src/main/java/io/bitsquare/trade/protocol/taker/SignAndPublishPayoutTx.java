package io.bitsquare.trade.protocol.taker;

import com.google.bitcoin.core.Transaction;
import com.google.bitcoin.core.Utils;
import com.google.common.util.concurrent.FutureCallback;
import io.bitsquare.btc.WalletFacade;
import io.bitsquare.trade.protocol.FaultHandler;
import java.math.BigInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignAndPublishPayoutTx
{
    private static final Logger log = LoggerFactory.getLogger(SignAndPublishPayoutTx.class);

    public static void run(ResultHandler resultHandler,
                           FaultHandler faultHandler,
                           WalletFacade walletFacade,
                           String tradeId,
                           String depositTxAsHex,
                           String offererSignatureR,
                           String offererSignatureS,
                           BigInteger offererPaybackAmount,
                           BigInteger takerPaybackAmount,
                           String offererPayoutAddress)
    {
        log.trace("Run task");
        try
        {

            walletFacade.takerSignsAndSendsTx(depositTxAsHex,
                                              offererSignatureR,
                                              offererSignatureS,
                                              offererPaybackAmount,
                                              takerPaybackAmount,
                                              offererPayoutAddress,
                                              tradeId,
                                              new FutureCallback<Transaction>()
                                              {
                                                  @Override
                                                  public void onSuccess(Transaction transaction)
                                                  {
                                                      log.debug("takerSignsAndSendsTx " + transaction);
                                                      String payoutTxAsHex = Utils.bytesToHexString(transaction.bitcoinSerialize());
                                                      resultHandler.onResult(transaction.getHashAsString(), payoutTxAsHex);
                                                  }

                                                  @Override
                                                  public void onFailure(Throwable t)
                                                  {
                                                      log.error("Exception at takerSignsAndSendsTx " + t);
                                                      faultHandler.onFault(t);
                                                  }
                                              });
        } catch (Exception e)
        {
            log.error("Exception at takerSignsAndSendsTx " + e);
            faultHandler.onFault(e);
        }
    }

    public interface ResultHandler
    {
        void onResult(String transactionId, String payoutTxAsHex);
    }

}
