package com.tasks;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.services.component.FDDeployCompService;
import com.services.component.FDReleaseCompService;

public class ReleaseTask implements Runnable {

	String orgId;
	String token;
	String serverURL;
	String refreshToken;
	String releaseId;
	String releaseName;
	String releaseStatus;
	String metadataLogId;
	

	public ReleaseTask(String orgId, String token, String serverURL,
			String refreshToken, String releaseId, String releaseName,
			String releaseStatus,String metadataLogId) {
		this.orgId = orgId;
		this.token = token;
		this.refreshToken = refreshToken;
		this.serverURL = serverURL;
		this.releaseId = releaseId;
		this.releaseName = releaseName;
		this.releaseStatus = releaseStatus;
		this.metadataLogId=metadataLogId;
	}

	@Override
	public void run() {
		String errors = null;
		boolean errorFlag = false;
		try {
			FDReleaseCompService deployService = new FDReleaseCompService();
			deployService.release(getOrgId(), getToken(), getServerURL(),
					getRefreshToken(), getReleaseId(), getReleaseName(), getReleaseStatus(),getMetadataLogId());
		} catch (Exception e) {
			errorFlag = true;
			StringWriter lerrors = new StringWriter();
			e.printStackTrace(new PrintWriter(lerrors));
			errors = lerrors.toString();
		} finally {
			if (errorFlag) {
				System.out.println("Release Operation Complete for requestId: "
						+ getReleaseId() + "\nWith Errors: " + errors);
			} else {
				System.out.println("Release Operation Complete for requestId: "
						+ getReleaseId());
			}
		}
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getServerURL() {
		return serverURL;
	}

	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getReleaseId() {
		return releaseId;
	}

	public void setReleaseId(String releaseId) {
		this.releaseId = releaseId;
	}

	public String getReleaseName() {
		return releaseName;
	}

	public void setReleaseName(String releaseName) {
		this.releaseName = releaseName;
	}

	public String getReleaseStatus() {
		return releaseStatus;
	}

	public void setReleaseStatus(String releaseStatus) {
		this.releaseStatus = releaseStatus;
	}

	public String getMetadataLogId() {
		return metadataLogId;
	}

	public void setMetadataLogId(String metadataLogId) {
		this.metadataLogId = metadataLogId;
	}

	
}