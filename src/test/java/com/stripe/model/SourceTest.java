package com.stripe.model;

import static org.junit.Assert.assertNotNull;

import com.stripe.BaseStripeMockTest;
import com.stripe.net.APIResource;

import org.junit.Test;

public class SourceTest extends BaseStripeMockTest {
  @Test
  public void testDeserialize() throws Exception {
    String data = getFixture("/v1/sources/src_123");
    Source source = APIResource.GSON.fromJson(data, Source.class);
    assertNotNull(source);
    assertNotNull(source.getId());
  }
}
