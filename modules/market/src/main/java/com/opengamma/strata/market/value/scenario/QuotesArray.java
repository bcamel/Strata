/**
 * Copyright (C) 2015 - present by OpenGamma Inc. and the OpenGamma group of companies
 * <p>
 * Please see distribution for license.
 */
package com.opengamma.strata.market.value.scenario;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.strata.basics.market.ScenarioMarketDataValue;
import com.opengamma.strata.collect.array.DoubleArray;
import com.opengamma.strata.market.key.scenario.QuotesArrayKey;

/**
 * Container for values for an item of quoted market data in multiple scenarios.
 * <p>
 * This class is a more efficient alternative to storing quotes using {@code MarketDataBox.ofScenarioValues}
 * or {@code ScenarioValuesList}.
 * <p>
 * It stores the quote values in a primitive double array which reduces memory
 * footprint and avoids the overhead of boxing.
 * <p>
 * For maximum performance functions can access the array of quotes without boxing or copying via
 * the {@code quotes} property. Functions should use a {@link QuotesArrayKey} to request
 * a {@code QuotesArray} from the market data container if they need direct access to the array of quotes.
 */
@BeanDefinition(builderScope = "private")
public final class QuotesArray implements ScenarioMarketDataValue<Double>, ImmutableBean {

  /**
   * The values of the quotes.
   */
  @PropertyDefinition(validate = "notNull")
  private final DoubleArray quotes;

  //-------------------------------------------------------------------------
  /**
   * Obtains an instance wrapping a set of quotes.
   *
   * @param quotes  the quotes
   * @return an instance wrapping a set of quotes
   */
  public static QuotesArray of(DoubleArray quotes) {
    return new QuotesArray(quotes);
  }

  //-------------------------------------------------------------------------
  @Override
  public Double getValue(int scenarioIndex) {
    return quotes.get(scenarioIndex);
  }

  @Override
  public int getScenarioCount() {
    return quotes.size();
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code QuotesArray}.
   * @return the meta-bean, not null
   */
  public static QuotesArray.Meta meta() {
    return QuotesArray.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(QuotesArray.Meta.INSTANCE);
  }

  private QuotesArray(
      DoubleArray quotes) {
    JodaBeanUtils.notNull(quotes, "quotes");
    this.quotes = quotes;
  }

  @Override
  public QuotesArray.Meta metaBean() {
    return QuotesArray.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the values of the quotes.
   * @return the value of the property, not null
   */
  public DoubleArray getQuotes() {
    return quotes;
  }

  //-----------------------------------------------------------------------
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      QuotesArray other = (QuotesArray) obj;
      return JodaBeanUtils.equal(quotes, other.quotes);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(quotes);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("QuotesArray{");
    buf.append("quotes").append('=').append(JodaBeanUtils.toString(quotes));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code QuotesArray}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code quotes} property.
     */
    private final MetaProperty<DoubleArray> quotes = DirectMetaProperty.ofImmutable(
        this, "quotes", QuotesArray.class, DoubleArray.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "quotes");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -948399753:  // quotes
          return quotes;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends QuotesArray> builder() {
      return new QuotesArray.Builder();
    }

    @Override
    public Class<? extends QuotesArray> beanType() {
      return QuotesArray.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code quotes} property.
     * @return the meta-property, not null
     */
    public MetaProperty<DoubleArray> quotes() {
      return quotes;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -948399753:  // quotes
          return ((QuotesArray) bean).getQuotes();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code QuotesArray}.
   */
  private static final class Builder extends DirectFieldsBeanBuilder<QuotesArray> {

    private DoubleArray quotes;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -948399753:  // quotes
          return quotes;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -948399753:  // quotes
          this.quotes = (DoubleArray) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public QuotesArray build() {
      return new QuotesArray(
          quotes);
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(64);
      buf.append("QuotesArray.Builder{");
      buf.append("quotes").append('=').append(JodaBeanUtils.toString(quotes));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
