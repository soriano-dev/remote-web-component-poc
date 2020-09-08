/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.remote.web.component.uad.anonymizer;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.remote.web.component.model.RemoteWebComponentEntry;
import com.liferay.remote.web.component.service.RemoteWebComponentEntryLocalService;
import com.liferay.remote.web.component.uad.constants.RemoteWebComponentUADConstants;
import com.liferay.user.associated.data.anonymizer.DynamicQueryUADAnonymizer;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the remote web component entry UAD anonymizer.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link RemoteWebComponentEntryUADAnonymizer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseRemoteWebComponentEntryUADAnonymizer
	extends DynamicQueryUADAnonymizer<RemoteWebComponentEntry> {

	@Override
	public void autoAnonymize(
			RemoteWebComponentEntry remoteWebComponentEntry, long userId,
			User anonymousUser)
		throws PortalException {

		if (remoteWebComponentEntry.getUserId() == userId) {
			remoteWebComponentEntry.setUserId(anonymousUser.getUserId());
			remoteWebComponentEntry.setUserName(anonymousUser.getFullName());
		}

		remoteWebComponentEntryLocalService.updateRemoteWebComponentEntry(
			remoteWebComponentEntry);
	}

	@Override
	public void delete(RemoteWebComponentEntry remoteWebComponentEntry)
		throws PortalException {

		remoteWebComponentEntryLocalService.deleteRemoteWebComponentEntry(
			remoteWebComponentEntry);
	}

	@Override
	public Class<RemoteWebComponentEntry> getTypeClass() {
		return RemoteWebComponentEntry.class;
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return remoteWebComponentEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return RemoteWebComponentUADConstants.
			USER_ID_FIELD_NAMES_REMOTE_WEB_COMPONENT_ENTRY;
	}

	@Reference
	protected RemoteWebComponentEntryLocalService
		remoteWebComponentEntryLocalService;

}