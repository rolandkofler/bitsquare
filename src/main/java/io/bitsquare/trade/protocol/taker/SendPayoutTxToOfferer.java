package io.bitsquare.trade.protocol.taker;

import io.bitsquare.msg.MessageFacade;
import io.bitsquare.msg.listeners.OutgoingTradeMessageListener;
import io.bitsquare.trade.protocol.FaultHandler;
import io.bitsquare.trade.protocol.ResultHandler;
import net.tomp2p.peers.PeerAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendPayoutTxToOfferer
{
    private static final Logger log = LoggerFactory.getLogger(SendPayoutTxToOfferer.class);

    public static void run(ResultHandler resultHandler, FaultHandler faultHandler, PeerAddress peerAddress, MessageFacade messageFacade, String tradeId, String payoutTxAsHex)
    {
        log.trace("Run task");
        PayoutTxPublishedMessage tradeMessage = new PayoutTxPublishedMessage(tradeId, payoutTxAsHex);
        messageFacade.sendTradeMessage(peerAddress, tradeMessage, new OutgoingTradeMessageListener()
        {
            @Override
            public void onResult()
            {
                log.trace("PayoutTxPublishedMessage successfully arrived at peer");
                resultHandler.onResult();
            }

            @Override
            public void onFailed()
            {
                log.error("PayoutTxPublishedMessage  did not arrive at peer");
                faultHandler.onFault(new Exception("PayoutTxPublishedMessage did not arrive at peer"));
            }
        });

    }

}
