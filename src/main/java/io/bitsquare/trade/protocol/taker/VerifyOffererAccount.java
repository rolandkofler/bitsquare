package io.bitsquare.trade.protocol.taker;

import io.bitsquare.bank.BankAccount;
import io.bitsquare.btc.BlockChainFacade;
import io.bitsquare.trade.protocol.FaultHandler;
import io.bitsquare.trade.protocol.ResultHandler;
import io.bitsquare.trade.protocol.shared.VerifyPeerAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerifyOffererAccount
{
    private static final Logger log = LoggerFactory.getLogger(VerifyOffererAccount.class);

    public static void run(ResultHandler resultHandler, FaultHandler faultHandler, BlockChainFacade blockChainFacade, String peersAccountId, BankAccount peersBankAccount)
    {
        log.trace("Run task");
        VerifyPeerAccount.run(resultHandler, faultHandler, blockChainFacade, peersAccountId, peersBankAccount);
    }
}
