package com.opengamma.strata.pricer.credit.cds;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
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

import com.opengamma.strata.basics.StandardId;
import com.opengamma.strata.basics.currency.Currency;
import com.opengamma.strata.market.param.CurrencyParameterSensitivities;
import com.opengamma.strata.pricer.ZeroRateSensitivity;

@BeanDefinition
public final class LegalEntitySurvivalProbabilities
    implements ImmutableBean, Serializable {

  /**
   * The currency that the discount factors are for.
   */
  @PropertyDefinition(validate = "notNull")
  private final Currency currency;

  @PropertyDefinition(validate = "notNull")
  private final StandardId legalEntityId;
//  /** 
//   * The underlying curve.
//   * The metadata of the curve must define a day count.
//   */
//  @PropertyDefinition(get = "optional")
//  private final ImmutableMap<Pair<LocalDate, LocalDate>, DiscountFactors> uniqueCurve;  // TODO extra structure for index
  /**
   * The underlying curve.
   * The metadata of the curve must define a day count.
   */
  @PropertyDefinition(validate = "notNull")
  private final CreditDiscountFactors survivalProbabilities;

//  public double survivalProbability(Pair<LocalDate, LocalDate> startEnd, LocalDate date) {
//    if (getUniqueCurve().isPresent()) {
//      DiscountFactors single = getUniqueCurve().get().get(startEnd);
//      if (single != null) {
//        return single.discountFactor(date);
//      }
//    }
//    return curve.discountFactor(date);
//  }

  public double survivalProbability(LocalDate date) {
    return survivalProbabilities.discountFactor(date);
  }

  public double zeroRateYearFraction(LocalDate date) {
    double yearFraction = survivalProbabilities.relativeYearFraction(date);
    double zeroRate = survivalProbabilities.zeroRate(yearFraction);
    return yearFraction * zeroRate;
  }

  public CreditCurveZeroRateSensitivity zeroRatePointSensitivity(LocalDate date) {
    return zeroRatePointSensitivity(date, getCurrency());
  }

  public CreditCurveZeroRateSensitivity zeroRatePointSensitivity(LocalDate date, Currency sensitivityCurrency) {
    ZeroRateSensitivity zeroRateSensitivity = survivalProbabilities.zeroRatePointSensitivity(date, sensitivityCurrency);
    return CreditCurveZeroRateSensitivity.of(zeroRateSensitivity, legalEntityId);
  }

  public CurrencyParameterSensitivities parameterSensitivity(CreditCurveZeroRateSensitivity pointSensitivity) {
    return survivalProbabilities.parameterSensitivity(pointSensitivity.createZeroRateSensitivity());
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code LegalEntitySurvivalProbabilities}.
   * @return the meta-bean, not null
   */
  public static LegalEntitySurvivalProbabilities.Meta meta() {
    return LegalEntitySurvivalProbabilities.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(LegalEntitySurvivalProbabilities.Meta.INSTANCE);
  }

  /**
   * The serialization version id.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static LegalEntitySurvivalProbabilities.Builder builder() {
    return new LegalEntitySurvivalProbabilities.Builder();
  }

  private LegalEntitySurvivalProbabilities(
      Currency currency,
      StandardId legalEntityId,
      CreditDiscountFactors survivalProbabilities) {
    JodaBeanUtils.notNull(currency, "currency");
    JodaBeanUtils.notNull(legalEntityId, "legalEntityId");
    JodaBeanUtils.notNull(survivalProbabilities, "survivalProbabilities");
    this.currency = currency;
    this.legalEntityId = legalEntityId;
    this.survivalProbabilities = survivalProbabilities;
  }

  @Override
  public LegalEntitySurvivalProbabilities.Meta metaBean() {
    return LegalEntitySurvivalProbabilities.Meta.INSTANCE;
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
   * Gets the currency that the discount factors are for.
   * @return the value of the property, not null
   */
  public Currency getCurrency() {
    return currency;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the legalEntityId.
   * @return the value of the property, not null
   */
  public StandardId getLegalEntityId() {
    return legalEntityId;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the underlying curve.
   * The metadata of the curve must define a day count.
   * @return the value of the property, not null
   */
  public CreditDiscountFactors getSurvivalProbabilities() {
    return survivalProbabilities;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      LegalEntitySurvivalProbabilities other = (LegalEntitySurvivalProbabilities) obj;
      return JodaBeanUtils.equal(currency, other.currency) &&
          JodaBeanUtils.equal(legalEntityId, other.legalEntityId) &&
          JodaBeanUtils.equal(survivalProbabilities, other.survivalProbabilities);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(currency);
    hash = hash * 31 + JodaBeanUtils.hashCode(legalEntityId);
    hash = hash * 31 + JodaBeanUtils.hashCode(survivalProbabilities);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(128);
    buf.append("LegalEntitySurvivalProbabilities{");
    buf.append("currency").append('=').append(currency).append(',').append(' ');
    buf.append("legalEntityId").append('=').append(legalEntityId).append(',').append(' ');
    buf.append("survivalProbabilities").append('=').append(JodaBeanUtils.toString(survivalProbabilities));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code LegalEntitySurvivalProbabilities}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code currency} property.
     */
    private final MetaProperty<Currency> currency = DirectMetaProperty.ofImmutable(
        this, "currency", LegalEntitySurvivalProbabilities.class, Currency.class);
    /**
     * The meta-property for the {@code legalEntityId} property.
     */
    private final MetaProperty<StandardId> legalEntityId = DirectMetaProperty.ofImmutable(
        this, "legalEntityId", LegalEntitySurvivalProbabilities.class, StandardId.class);
    /**
     * The meta-property for the {@code survivalProbabilities} property.
     */
    private final MetaProperty<CreditDiscountFactors> survivalProbabilities = DirectMetaProperty.ofImmutable(
        this, "survivalProbabilities", LegalEntitySurvivalProbabilities.class, CreditDiscountFactors.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "currency",
        "legalEntityId",
        "survivalProbabilities");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 575402001:  // currency
          return currency;
        case 866287159:  // legalEntityId
          return legalEntityId;
        case -2020275979:  // survivalProbabilities
          return survivalProbabilities;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public LegalEntitySurvivalProbabilities.Builder builder() {
      return new LegalEntitySurvivalProbabilities.Builder();
    }

    @Override
    public Class<? extends LegalEntitySurvivalProbabilities> beanType() {
      return LegalEntitySurvivalProbabilities.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code currency} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Currency> currency() {
      return currency;
    }

    /**
     * The meta-property for the {@code legalEntityId} property.
     * @return the meta-property, not null
     */
    public MetaProperty<StandardId> legalEntityId() {
      return legalEntityId;
    }

    /**
     * The meta-property for the {@code survivalProbabilities} property.
     * @return the meta-property, not null
     */
    public MetaProperty<CreditDiscountFactors> survivalProbabilities() {
      return survivalProbabilities;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 575402001:  // currency
          return ((LegalEntitySurvivalProbabilities) bean).getCurrency();
        case 866287159:  // legalEntityId
          return ((LegalEntitySurvivalProbabilities) bean).getLegalEntityId();
        case -2020275979:  // survivalProbabilities
          return ((LegalEntitySurvivalProbabilities) bean).getSurvivalProbabilities();
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
   * The bean-builder for {@code LegalEntitySurvivalProbabilities}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<LegalEntitySurvivalProbabilities> {

    private Currency currency;
    private StandardId legalEntityId;
    private CreditDiscountFactors survivalProbabilities;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(LegalEntitySurvivalProbabilities beanToCopy) {
      this.currency = beanToCopy.getCurrency();
      this.legalEntityId = beanToCopy.getLegalEntityId();
      this.survivalProbabilities = beanToCopy.getSurvivalProbabilities();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 575402001:  // currency
          return currency;
        case 866287159:  // legalEntityId
          return legalEntityId;
        case -2020275979:  // survivalProbabilities
          return survivalProbabilities;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 575402001:  // currency
          this.currency = (Currency) newValue;
          break;
        case 866287159:  // legalEntityId
          this.legalEntityId = (StandardId) newValue;
          break;
        case -2020275979:  // survivalProbabilities
          this.survivalProbabilities = (CreditDiscountFactors) newValue;
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
    public LegalEntitySurvivalProbabilities build() {
      return new LegalEntitySurvivalProbabilities(
          currency,
          legalEntityId,
          survivalProbabilities);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the currency that the discount factors are for.
     * @param currency  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder currency(Currency currency) {
      JodaBeanUtils.notNull(currency, "currency");
      this.currency = currency;
      return this;
    }

    /**
     * Sets the legalEntityId.
     * @param legalEntityId  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder legalEntityId(StandardId legalEntityId) {
      JodaBeanUtils.notNull(legalEntityId, "legalEntityId");
      this.legalEntityId = legalEntityId;
      return this;
    }

    /**
     * Sets the underlying curve.
     * The metadata of the curve must define a day count.
     * @param survivalProbabilities  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder survivalProbabilities(CreditDiscountFactors survivalProbabilities) {
      JodaBeanUtils.notNull(survivalProbabilities, "survivalProbabilities");
      this.survivalProbabilities = survivalProbabilities;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(128);
      buf.append("LegalEntitySurvivalProbabilities.Builder{");
      buf.append("currency").append('=').append(JodaBeanUtils.toString(currency)).append(',').append(' ');
      buf.append("legalEntityId").append('=').append(JodaBeanUtils.toString(legalEntityId)).append(',').append(' ');
      buf.append("survivalProbabilities").append('=').append(JodaBeanUtils.toString(survivalProbabilities));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}