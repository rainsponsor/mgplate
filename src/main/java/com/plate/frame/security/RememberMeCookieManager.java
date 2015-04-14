
package com.plate.frame.security;

import org.apache.shiro.mgt.AbstractRememberMeManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;

/**
 *作者： 李志刚
 *时间：2015-1-20
 */
public class RememberMeCookieManager extends AbstractRememberMeManager{

	@Override
	public void forgetIdentity(SubjectContext subjectContext) {
		// TODO Auto-generated
	}

	@Override
	protected void forgetIdentity(Subject subject) {
		// TODO Auto-generated
			
	}

	@Override
	protected void rememberSerializedIdentity(Subject subject, byte[] serialized) {
		// TODO Auto-generated
			
	}

	@Override
	protected byte[] getRememberedSerializedIdentity(SubjectContext subjectContext) {
		// TODO Auto-generated
				return null;
			
	}

}

	