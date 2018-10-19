package org.apereo.cas.support.saml.services.idp.metadata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * This is {@link SamlIdPMetadataDocument}.
 *
 * @author Misagh Moayyed
 * @since 6.0.0
 */
@Entity
@Table(name = "SamlIdPMetadataDocument")
@Document
@Getter
@Setter
@AllArgsConstructor
public class SamlIdPMetadataDocument {

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", columnDefinition = "BIGINT")
    @JsonProperty
    private long id = -1;

    @Lob
    @Type(type = "org.hibernate.type.StringNVarcharType")
    @Column(name = "metadata", length = 8_000)
    @JsonProperty
    private String metadata;

    @Lob
    @Type(type = "org.hibernate.type.StringNVarcharType")
    @Column(name = "signingCertificate", length = 3_000)
    @JsonProperty
    private String signingCertificate;

    @Lob
    @Type(type = "org.hibernate.type.StringNVarcharType")
    @Column(name = "signingKey", length = 3_000)
    @JsonProperty
    private String signingKey;

    @Lob
    @Type(type = "org.hibernate.type.StringNVarcharType")
    @Column(name = "encryptionCertificate", length = 3_000)
    @JsonProperty
    private String encryptionCertificate;

    @Lob
    @Type(type = "org.hibernate.type.StringNVarcharType")
    @Column(name = "encryptionKey", length = 3_000)
    @JsonProperty
    private String encryptionKey;

    public SamlIdPMetadataDocument() {
        setId(System.nanoTime());
    }

    /**
     * Is valid?
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isValid() {
        return StringUtils.isNotBlank(getMetadata())
            && StringUtils.isNotBlank(getSigningCertificate())
            && StringUtils.isNotBlank(getSigningKey())
            && StringUtils.isNotBlank(getEncryptionCertificate())
            && StringUtils.isNotBlank(getEncryptionKey());
    }
}
