package com.stripe.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.stripe.BaseStripeMockTest;
import com.stripe.exception.StripeException;
import com.stripe.model.Transfer;
import com.stripe.model.TransferCollection;
import com.stripe.net.APIResource;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TransferTest extends BaseStripeMockTest {
  public static final String RESOURCE_ID = "tr_123";

  @Test
  public void testCreate() throws StripeException {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("amount", 100);
    params.put("currency", "usd");
    params.put("destination", "acct_123");

    Transfer transfer = Transfer.create(params);

    assertNotNull(transfer);
    verifyRequest(
        APIResource.RequestMethod.POST,
        "/v1/transfers",
        params
    );
  }

  @Test
  public void testRetrieve() throws StripeException {
    Transfer transfer = Transfer.retrieve(RESOURCE_ID);

    assertNotNull(transfer);
    verifyRequest(
        APIResource.RequestMethod.GET,
        String.format("/v1/transfers/%s", RESOURCE_ID)
    );
  }

  @Test
  public void testUpdate() throws StripeException {
    Transfer transfer = Transfer.retrieve(RESOURCE_ID);

    Map<String, Object> metadata = new HashMap<String, Object>();
    metadata.put("key", "value");
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("metadata", metadata);

    Transfer updatedTransfer = transfer.update(params);

    assertNotNull(updatedTransfer);
    verifyRequest(
        APIResource.RequestMethod.POST,
        String.format("/v1/transfers/%s", transfer.getId()),
        params
    );
  }

  @Test
  public void testList() throws StripeException {
    TransferCollection transfers = Transfer.list(null);

    assertNotNull(transfers);
    assertEquals(1, transfers.getData().size());
    verifyRequest(
        APIResource.RequestMethod.GET,
        "/v1/transfers"
    );
  }
}
