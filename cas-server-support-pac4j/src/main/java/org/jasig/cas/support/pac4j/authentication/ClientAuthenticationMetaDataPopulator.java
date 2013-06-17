/*
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.cas.support.pac4j.authentication;

import org.jasig.cas.authentication.Authentication;
import org.jasig.cas.authentication.AuthenticationBuilder;
import org.jasig.cas.authentication.AuthenticationMetaDataPopulator;
import org.jasig.cas.authentication.Credential;
import org.jasig.cas.authentication.principal.SimplePrincipal;
import org.jasig.cas.support.pac4j.authentication.principal.ClientCredential;

/**
 * This class is a meta data populator for authentication. As attributes are stored in ClientCredential (inside the
 * user profile), they are added to the returned principal. The client name associated to the authentication is also
 * added to the authentication attributes.
 *
 * @author Jerome Leleu
 * @since 3.5.0
 */
public final class ClientAuthenticationMetaDataPopulator implements AuthenticationMetaDataPopulator {

    /***
     * The name of the client used to perform the authentication.
     */
    public static final String CLIENT_NAME = "clientName";

    /**
     * {@inheritDoc}
     */
    @Override
    public void populateAttributes(final AuthenticationBuilder builder, final Credential credential) {
        if (credential instanceof ClientCredential) {
            final ClientCredential clientCredentials = (ClientCredential) credential;
            final Authentication temp = builder.build();
            builder.setPrincipal(new SimplePrincipal(
                    temp.getPrincipal().getId(),
                    clientCredentials.getUserProfile().getAttributes()));
            builder.addAttribute(CLIENT_NAME, clientCredentials.getCredentials().getClientName());
        }
    }
}
