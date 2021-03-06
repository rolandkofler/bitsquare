package io.bitsquare.trade.protocol.old;

//TODO not used but let it for reference until all use cases are impl.
class SellOffererPaymentProcess extends PaymentProcess
{
    public SellOffererPaymentProcess()
    {
        super();
    }


    // case 2 offerer step 1
    private void sellOfferOfferer_waitForCollateralPayedByPeer()
    {
        onDataDepositTx();
    }

    // case 2 offerer step 2
    private void sellOfferOfferer_payToDeposit()
    {
        payPaymentAndCollateral();
        signDepositTx();
        publishDepositTx();
        sendMessageDepositTxPublished();
    }

    // case 2 offerer step 3
    private void sellOfferOfferer_waitForFiat()
    {
        onMessageFiatTxInited();
        onDataPayoutTx();
        onUserInputFiatReceived();
    }

    // case 2 offerer step 4
    private void sellOfferOfferer_releasePayment()
    {
        signPayoutTx();
        publishPayoutTx();
        sendMessagePayoutTxPublished();
        onBlockChainConfirmation();
        done();
    }

}
