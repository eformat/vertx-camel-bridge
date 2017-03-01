/*
 *  Copyright (c) 2011-2015 The original author or authors
 *  ------------------------------------------------------
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *
 *       The Eclipse Public License is available at
 *       http://www.eclipse.org/legal/epl-v10.html
 *
 *       The Apache License v2.0 is available at
 *       http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 */
package io.vertx.camel;

import io.vertx.core.WorkerExecutor;
import org.apache.camel.Endpoint;

import java.util.Objects;

/**
 * Represents a mapping between a Vert.x event bus address and a Camel endpoint.
 *
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
public class OutboundMapping extends CamelMapping {

  private boolean blocking = false;
  private WorkerExecutor worker;

  /**
   * Creates an {@link OutboundMapping} from the given Vert.x address.
   *
   * @param address the address - must not be {@code null}
   * @return the created {@link OutboundMapping}
   */
  public static OutboundMapping fromVertx(String address) {
    Objects.requireNonNull(address);
    return new OutboundMapping().setAddress(address);
  }

  @Override
  public OutboundMapping setAddress(String address) {
    super.setAddress(address);
    return this;
  }

  @Override
  public OutboundMapping setHeadersCopy(boolean copyHeaders) {
    super.setHeadersCopy(copyHeaders);
    return this;
  }

  @Override
  public OutboundMapping setUri(String uri) {
    super.setUri(uri);
    return this;
  }

  @Override
  public OutboundMapping setEndpoint(Endpoint endpoint) {
    super.setEndpoint(endpoint);
    return this;
  }

  /**
   * Fluent version of {@link #setUri(String)}.
   *
   * @param uri the uri
   * @return the current instance of {@link OutboundMapping}
   */
  public OutboundMapping toCamel(String uri) {
    return setUri(uri);
  }

  /**
   * Fluent version of {@link #setEndpoint(Endpoint)}.
   *
   * @param endpoint the endpoint
   * @return the current instance of {@link OutboundMapping}
   */
  public OutboundMapping toCamel(Endpoint endpoint) {
    return setEndpoint(endpoint);
  }

  /**
   * Fluent version of {@link #setHeadersCopy(boolean)} to disable the headers copy.
   *
   * @return the current instance of {@link OutboundMapping}
   */
  public OutboundMapping withoutHeadersCopy() {
    return setHeadersCopy(false);
  }

  /**
   * Whether the processing is blocking and so should not be executed on the event loop.
   *
   * @return whether or not the processing is blocking
   */
  public boolean isBlocking() {
    return blocking;
  }

  /**
   * Sets whether or not the processing is blocking. {@code false} by default.
   *
   * @param blocking {@code true} to set it to blocking.
   * @return the current instance of {@link OutboundMapping}
   */
  public OutboundMapping setBlocking(boolean blocking) {
    this.blocking = blocking;
    return this;
  }

  /**
   * @return the worker thread worker to use to execute the processing. This option is only used if blocking is set to
   * {@code true}. If not set, it uses the the default worker worker.
   */
  public WorkerExecutor getWorkerExecutor() {
    return worker;
  }

  /**
   * Sets the worker thread worker used to execute the blocking processing. This option is only used if blocking is set to
   * {@code true}. If not set, it uses the the default worker worker.
   *
   * @param pool the worker worker on which the code is executed
   * @return the current instance of {@link OutboundMapping}
   */
  public OutboundMapping setWorkerExecutor(WorkerExecutor pool) {
    this.worker = pool;
    return this;
  }
}
