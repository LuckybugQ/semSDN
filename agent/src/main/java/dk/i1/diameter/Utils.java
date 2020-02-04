package dk.i1.diameter;
import java.util.*;

/**
 * A mishmash of handy methods
 */
final public class Utils {
	private Utils() {} //stop javadoc from listing this.
	
	private static final boolean contains(int[] a, int x) {
		for(int i : a)
			if(i==x)
				return true;
		return false;
	}
	private static final int empty_array[] = {};
	
	private static final boolean setMandatory(AVP a, int codes[], int grouped_avp_codes[]) {
		boolean modified = false;
		if(a.vendor_id==0 && contains(grouped_avp_codes,a.code)) {
			try {
				AVP_Grouped avp_g = new AVP_Grouped(a);
				AVP[] avp_g_avps = avp_g.queryAVPs();
				for(AVP avp_g_avp : avp_g_avps)
					modified = setMandatory(avp_g_avp,codes,grouped_avp_codes) || modified;
				boolean any_mandatory=false;
				for(AVP avp_g_avp : avp_g_avps)
					any_mandatory = any_mandatory || avp_g_avp.isMandatory();
				if(any_mandatory && !a.isMandatory()) {
					avp_g.setMandatory(true);
					modified = true;
				}
				if(modified) {
					avp_g.setAVPs(avp_g_avps);
					a.inline_shallow_replace(avp_g);
				}
			} catch(InvalidAVPLengthException ex) {
				//obviously not grouped - ignored
			}
		}
		if(!a.isMandatory()) {
			if(a.vendor_id==0 && contains(codes,a.code)) {
				a.setMandatory(true);
				modified = true;
			}
		}
		return modified;
	}
	
	/**
	 * Sets the M-bit on the avps with the specified codes.
	 * Vendor-specific AVPs are not modified.
	 * AVPs not listed are not modified.
	 * @param avps The AVPs to examine and possibly set the M-bit on.
	 * @param codes Array of codes
	 * @param grouped_avp_codes Array of AVP codes for AVPs that are grouped and should be examined deeper for other mandatory AVPs
	 * @since 0.9.5
	 */
	public static final void setMandatory(Iterable<AVP> avps, int codes[], int grouped_avp_codes[]) {
		for(AVP a : avps) {
			setMandatory(a,codes,grouped_avp_codes);
		}
	}
	/**
	 * Sets the M-bit on the avps with the specified codes.
	 * Vendor-specific AVPs are not modified.
	 * AVPs not listed are not modified.
	 * @param avps The AVPs to examine and possibly set the M-bit on.
	 * @param codes Array of codes
	 */
	public static final void setMandatory(Iterable<AVP> avps, int codes[]) {
		setMandatory(avps,codes,empty_array);
	}
	/**
	 * Sets the M-bit on the avps with the specified codes.
	 * Vendor-specific AVPs are not modified.
	 * AVPs not listed are not modified.
	 * @param avps The AVPs to examine and possibly set the M-bit on.
	 * @param codes Array of codes
	 */
	public static final void setMandatory(Iterable<AVP> avps, Collection<Integer> codes) {
		for(AVP a : avps) {
			if(codes.contains(a.code) && a.vendor_id==0) {
				a.setMandatory(true);
			}
		}
	}
	/**
	 * Sets the M-bit on the avps with the specified codes.
	 * @param msg The message AVPs to examine and possibly set the M-bit on.
	 * @param codes Array of codes
	 */
	public static final void setMandatory(Message msg, int codes[]) {
		setMandatory(msg.avps(),codes,empty_array);
	}
	/**
	 * Sets the M-bit on the avps with the specified codes.
	 * @param msg The message AVPs to examine and possibly set the M-bit on.
	 * @param codes Array of codes
	 * @param grouped_avp_codes Array of AVP codes for AVPs that are grouped and should be examined deeper for other mandatory AVPs
	 * @since 0.9.5
	 */
	public static final void setMandatory(Message msg, int codes[], int grouped_avp_codes[]) {
		setMandatory(msg.avps(),codes,grouped_avp_codes);
	}
	
	/**The AVP codes of the AVPs listen in RFC3588 section 4.5 that must be mandatory*/
	public static final int rfc3588_mandatory_codes[]={
		ProtocolConstants.DI_ACCOUNTING_REALTIME_REQUIRED,
		ProtocolConstants.DI_ACCOUNTING_RECORD_NUMBER,
		ProtocolConstants.DI_ACCOUNTING_RECORD_TYPE,
		ProtocolConstants.DI_ACCOUNTING_SESSION_ID,
		ProtocolConstants.DI_ACCOUNTING_SUB_SESSION_ID,
		ProtocolConstants.DI_ACCT_APPLICATION_ID,
		ProtocolConstants.DI_ACCT_INTERIM_INTERVAL,
		ProtocolConstants.DI_ACCT_MULTI_SESSION_ID,
		ProtocolConstants.DI_AUTHORIZATION_LIFETIME,
		ProtocolConstants.DI_AUTH_APPLICATION_ID,
		ProtocolConstants.DI_AUTH_GRACE_PERIOD,
		ProtocolConstants.DI_AUTH_REQUEST_TYPE,
		ProtocolConstants.DI_AUTH_SESSION_STATE,
		ProtocolConstants.DI_CLASS,
		ProtocolConstants.DI_DESTINATION_HOST,
		ProtocolConstants.DI_DESTINATION_REALM,
		ProtocolConstants.DI_DISCONNECT_CAUSE,
		ProtocolConstants.DI_E2E_SEQUENCE_AVP,
		ProtocolConstants.DI_EVENT_TIMESTAMP,
		ProtocolConstants.DI_EXPERIMENTAL_RESULT,
		ProtocolConstants.DI_EXPERIMENTAL_RESULT_CODE,
		ProtocolConstants.DI_FAILED_AVP,
		ProtocolConstants.DI_HOST_IP_ADDRESS,
		ProtocolConstants.DI_INBAND_SECURITY_ID,
		ProtocolConstants.DI_MULTI_ROUND_TIME_OUT,
		ProtocolConstants.DI_ORIGIN_HOST,
		ProtocolConstants.DI_ORIGIN_REALM,
		ProtocolConstants.DI_ORIGIN_STATE_ID,
		ProtocolConstants.DI_PROXY_HOST,
		ProtocolConstants.DI_PROXY_INFO,
		ProtocolConstants.DI_PROXY_STATE,
		ProtocolConstants.DI_REDIRECT_HOST,
		ProtocolConstants.DI_REDIRECT_HOST_USAGE,
		ProtocolConstants.DI_REDIRECT_MAX_CACHE_TIME,
		ProtocolConstants.DI_RESULT_CODE,
		ProtocolConstants.DI_RE_AUTH_REQUEST_TYPE,
		ProtocolConstants.DI_ROUTE_RECORD,
		ProtocolConstants.DI_SESSION_BINDING,
		ProtocolConstants.DI_SESSION_ID,
		ProtocolConstants.DI_SESSION_SERVER_FAILOVER,
		ProtocolConstants.DI_SESSION_TIMEOUT,
		ProtocolConstants.DI_SUPPORTED_VENDOR_ID,
		ProtocolConstants.DI_TERMINATION_CAUSE,
		ProtocolConstants.DI_USER_NAME,
		ProtocolConstants.DI_VENDOR_ID,
		ProtocolConstants.DI_VENDOR_SPECIFIC_APPLICATION_ID
	};
	/**List of AVPs that are grouped according to RFC3588 section 4.5
	 *@since 0.9.5
	 */
	public static final int rfc3588_grouped_avps[]={
		ProtocolConstants.DI_E2E_SEQUENCE_AVP,
		ProtocolConstants.DI_EXPERIMENTAL_RESULT,
		ProtocolConstants.DI_FAILED_AVP,
		ProtocolConstants.DI_PROXY_INFO,
		ProtocolConstants.DI_VENDOR_SPECIFIC_APPLICATION_ID
	};
	
	/**Sets the M-bit on the AVPs that should have the M bit set according to RFC3588
	 * <p>New behaviour since 0.9.5: Also traverses grouped AVPs mentioned in rfc4006 and handles the M-bit properly.
	 */
	public static final void setMandatory_RFC3588(Iterable<AVP> avps) {
		setMandatory(avps,rfc3588_mandatory_codes,rfc3588_grouped_avps);
	}
	/**Sets the M-bit on the AVPs that should have the M bit set according to RFC3588
	 * <p>New behaviour since 0.9.5: Also traverses grouped AVPs mentioned in rfc4006 and handles the M-bit properly.
	 */
	public static final void setMandatory_RFC3588(Message msg) {
		setMandatory(msg.avps(),rfc3588_mandatory_codes,rfc3588_grouped_avps);
	}
	
	/**The AVP codes of the AVPs listen in RFC4006 section 8 that must be mandatory*/
	public static final int rfc4006_mandatory_codes[]={
		ProtocolConstants.DI_CC_INPUT_OCTETS,
		ProtocolConstants.DI_CC_MONEY,
		ProtocolConstants.DI_CC_OUTPUT_OCTETS,
		ProtocolConstants.DI_CC_REQUEST_NUMBER,
		ProtocolConstants.DI_CC_REQUEST_TYPE,
		ProtocolConstants.DI_CC_SERVICE_SPECIFIC_UNITS,
		ProtocolConstants.DI_CC_SESSION_FAILOVER,
		ProtocolConstants.DI_CC_SUB_SESSION_ID,
		ProtocolConstants.DI_CC_TIME,
		ProtocolConstants.DI_CC_TOTAL_OCTETS,
		ProtocolConstants.DI_CC_UNIT_TYPE,
		ProtocolConstants.DI_CHECK_BALANCE_RESULT,
		ProtocolConstants.DI_COST_INFORMATION,
		ProtocolConstants.DI_COST_UNIT,
		ProtocolConstants.DI_CREDIT_CONTROL,
		ProtocolConstants.DI_CREDIT_CONTROL_FAILURE_HANDLING,
		ProtocolConstants.DI_CURRENCY_CODE,
		ProtocolConstants.DI_DIRECT_DEBITING_FAILURE_HANDLING,
		ProtocolConstants.DI_EXPONENT,
		ProtocolConstants.DI_FINAL_UNIT_ACTION,
		ProtocolConstants.DI_FINAL_UNIT_INDICATION,
		ProtocolConstants.DI_GRANTED_SERVICE_UNIT,
		ProtocolConstants.DI_G_S_U_POOL_IDENTIFIER,
		ProtocolConstants.DI_G_S_U_POOL_REFERENCE,
		ProtocolConstants.DI_MULTIPLE_SERVICES_CREDIT_CONTROL,
		ProtocolConstants.DI_MULTIPLE_SERVICES_INDICATOR,
		ProtocolConstants.DI_RATING_GROUP,
		ProtocolConstants.DI_REDIRECT_ADDRESS_TYPE,
		ProtocolConstants.DI_REDIRECT_SERVER,
		ProtocolConstants.DI_REDIRECT_SERVER_ADDRESS,
		ProtocolConstants.DI_REQUESTED_ACTION,
		ProtocolConstants.DI_REQUESTED_SERVICE_UNIT,
		ProtocolConstants.DI_RESTRICTION_FILTER_RULE,
		ProtocolConstants.DI_SERVICE_CONTEXT_ID,
		ProtocolConstants.DI_SERVICE_IDENTIFIER,
		ProtocolConstants.DI_SUBSCRIPTION_ID,
		ProtocolConstants.DI_SUBSCRIPTION_ID_DATA,
		ProtocolConstants.DI_SUBSCRIPTION_ID_TYPE,
		ProtocolConstants.DI_TARIFF_CHANGE_USAGE,
		ProtocolConstants.DI_TARIFF_TIME_CHANGE,
		ProtocolConstants.DI_UNIT_VALUE,
		ProtocolConstants.DI_USED_SERVICE_UNIT,
		ProtocolConstants.DI_VALUE_DIGITS,
		ProtocolConstants.DI_VALIDITY_TIME,
	};
	/**List of AVPs that are grouped according to RFC4006 section 8
	 * @since 0.9.5
	 */
	public static final int rfc4006_grouped_avps[]={
		ProtocolConstants.DI_CC_MONEY,
		ProtocolConstants.DI_COST_INFORMATION,
		ProtocolConstants.DI_FINAL_UNIT_INDICATION,
		ProtocolConstants.DI_GRANTED_SERVICE_UNIT,
		ProtocolConstants.DI_G_S_U_POOL_REFERENCE,
		ProtocolConstants.DI_MULTIPLE_SERVICES_CREDIT_CONTROL,
		ProtocolConstants.DI_REDIRECT_SERVER,
		ProtocolConstants.DI_REQUESTED_SERVICE_UNIT,
		ProtocolConstants.DI_SERVICE_PARAMETER_INFO,
		ProtocolConstants.DI_SUBSCRIPTION_ID,
		ProtocolConstants.DI_UNIT_VALUE,
		ProtocolConstants.DI_USED_SERVICE_UNIT,
		ProtocolConstants.DI_USER_EQUIPMENT_INFO
	};
	
	/**Sets the M-bit on the AVPs that must have the M bit set according to RFC4006
	 * <p>New behaviour since 0.9.5: Also traverses grouped AVPs mentioned in rfc4006 and handles the M-bit properly.
	 * @since 0.9.2
	 */
	public static final void setMandatory_RFC4006(Iterable<AVP> avps) {
		setMandatory(avps,rfc4006_mandatory_codes,rfc4006_grouped_avps);
	}
	/**Sets the M-bit on the AVPs that must have the M bit set according to RFC4006
	 * <p>New behaviour since 0.9.5: Also traverses grouped AVPs mentioned in rfc4006 and handles the M-bit properly.
	 * @since 0.9.2
	 */
	public static final void setMandatory_RFC4006(Message msg) {
		setMandatory(msg.avps(),rfc4006_mandatory_codes,rfc4006_grouped_avps);
	}
	
	/**
	 * Copies any Proxy-Info AVPs from one message to another.
	 * @param from The source message.
	 * @param to The destination message.
	 */
	public static final void copyProxyInfo(Message from, Message to) {
		for(AVP avp : from.subset(ProtocolConstants.DI_PROXY_INFO))
			to.add(new AVP(avp));
	}
	
	
	/** A component in a message ABNF */
	static public class ABNFComponent {
		/*True if the AVP must occur at this posistion*/
		public boolean fixed_position;
		/**Minimum occurrences. 0 means that this AVP is optional*/
		public int min_count;
		/**Maximum occurrences. -1 means no limit*/
		public int max_count;
		/**AVP code*/
		public int code;
		public ABNFComponent(boolean fixed_position,
		                     int min_count, int max_count,
		                     int code)
		{
			this.fixed_position = fixed_position;
			this.min_count = min_count;
			this.max_count = max_count;
			this.code = code;
		}
	};
	
	/**ABNF for CER (section 5.3.1)*/
	public static final ABNFComponent abnf_cer[] = {
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_HOST),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_REALM),
		new ABNFComponent(false,  1, -1, ProtocolConstants.DI_HOST_IP_ADDRESS),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_VENDOR_ID),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_PRODUCT_NAME),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_ORIGIN_STATE_ID),
		new ABNFComponent(false,  0, -1, ProtocolConstants.DI_SUPPORTED_VENDOR_ID),
		new ABNFComponent(false,  0, -1, ProtocolConstants.DI_AUTH_APPLICATION_ID),
		new ABNFComponent(false,  0, -1, ProtocolConstants.DI_INBAND_SECURITY_ID),
		new ABNFComponent(false,  0, -1, ProtocolConstants.DI_ACCT_APPLICATION_ID),
		new ABNFComponent(false,  0, -1, ProtocolConstants.DI_VENDOR_SPECIFIC_APPLICATION_ID),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_FIRMWARE_REVISION),
		new ABNFComponent(false,  0, -1, -1),
	};
	/**ABNF for CEA (section 5.3.2)*/
	public static final ABNFComponent abnf_cea[] = {
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_RESULT_CODE),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_HOST),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_REALM),
		new ABNFComponent(false,  1, -1, ProtocolConstants.DI_HOST_IP_ADDRESS),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_VENDOR_ID),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_PRODUCT_NAME),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_ORIGIN_STATE_ID),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_ERROR_MESSAGE),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_FAILED_AVP),
		new ABNFComponent(false,  0, -1, ProtocolConstants.DI_SUPPORTED_VENDOR_ID),
		new ABNFComponent(false,  0, -1, ProtocolConstants.DI_AUTH_APPLICATION_ID),
		new ABNFComponent(false,  0, -1, ProtocolConstants.DI_INBAND_SECURITY_ID),
		new ABNFComponent(false,  0, -1, ProtocolConstants.DI_ACCT_APPLICATION_ID),
		new ABNFComponent(false,  0, -1, ProtocolConstants.DI_VENDOR_SPECIFIC_APPLICATION_ID),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_FIRMWARE_REVISION),
		new ABNFComponent(false,  0, -1, -1),
	};
	/**ABNF for DPR (section 5.4.1)*/
	public static final ABNFComponent abnf_dpr[] = {
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_HOST),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_REALM),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_DISCONNECT_CAUSE),
	};
	/**ABNF for DPA (section 5.4.2)*/
	public static final ABNFComponent abnf_dpa[] = {
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_RESULT_CODE),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_HOST),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_REALM),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_ERROR_MESSAGE),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_FAILED_AVP),
	};
	/**ABNF for DWR (section 5.5.1)*/
	public static final ABNFComponent abnf_dwr[] = {
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_HOST),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_REALM),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_ORIGIN_STATE_ID),
	};
	/**ABNF for DWA (section 5.5.2)*/
	public static final ABNFComponent abnf_dwa[] = {
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_RESULT_CODE),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_HOST),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_REALM),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_ERROR_MESSAGE),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_FAILED_AVP),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_ORIGIN_STATE_ID),
	};
	//todo: 7.2
	/**ABNF for RAR (section 8.3.1)*/
	public static final ABNFComponent abnf_rar[] = {
		new ABNFComponent(true,   1,  1, ProtocolConstants.DI_SESSION_ID),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_HOST),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_REALM),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_DESTINATION_REALM),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_DESTINATION_HOST),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_AUTH_APPLICATION_ID),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_RE_AUTH_REQUEST_TYPE),
		new ABNFComponent(false,  0, -1, -1),
	};
	/**ABNF for RAA (section 8.3.2)*/
	public static final ABNFComponent abnf_raa[] = {
		new ABNFComponent(true,   1,  1, ProtocolConstants.DI_SESSION_ID),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_RESULT_CODE),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_HOST),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_REALM),
		//more optional attributes, but no point in listing them because there can also be arbitrary AVPs
		new ABNFComponent(false,  0, -1, -1),
	};
	/**ABNF for STR (section 8.4.1)*/
	public static final ABNFComponent abnf_str[] = {
		new ABNFComponent(true,   1,  1, ProtocolConstants.DI_SESSION_ID),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_HOST),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_REALM),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_DESTINATION_REALM),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_AUTH_APPLICATION_ID),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_TERMINATION_CAUSE),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_USER_NAME),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_DESTINATION_HOST),
		new ABNFComponent(false,  0, -1, ProtocolConstants.DI_CLASS),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_ORIGIN_STATE_ID),
		new ABNFComponent(false,  0, -1, ProtocolConstants.DI_PROXY_INFO),
		new ABNFComponent(false,  0, -1, ProtocolConstants.DI_ROUTE_RECORD),
		new ABNFComponent(false,  0, -1, -1),
	};
	/**ABNF for STA (section 8.4.1)*/
	public static final ABNFComponent abnf_sta[] = {
		new ABNFComponent(true,   1,  1, ProtocolConstants.DI_SESSION_ID),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_RESULT_CODE),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_HOST),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_REALM),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_USER_NAME),
		new ABNFComponent(false,  0, -1, ProtocolConstants.DI_CLASS),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_ERROR_MESSAGE),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_ERROR_REPORTING_HOST),
		new ABNFComponent(false,  0, -1, ProtocolConstants.DI_FAILED_AVP),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_ORIGIN_STATE_ID),
		new ABNFComponent(false,  0, -1, ProtocolConstants.DI_REDIRECT_HOST),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_REDIRECT_HOST_USAGE),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_REDIRECT_MAX_CACHE_TIME),
		new ABNFComponent(false,  0, -1, ProtocolConstants.DI_PROXY_INFO),
		new ABNFComponent(false,  0, -1, -1),
	};
	/**ABNF for ASR (section 8.5.1)*/
	public static final ABNFComponent abnf_asr[] = {
		new ABNFComponent(true,   1,  1, ProtocolConstants.DI_SESSION_ID),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_HOST),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_REALM),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_DESTINATION_REALM),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_DESTINATION_HOST),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_AUTH_APPLICATION_ID),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_USER_NAME),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_ORIGIN_STATE_ID),
		new ABNFComponent(false,  0, -1, ProtocolConstants.DI_PROXY_INFO),
		new ABNFComponent(false,  0, -1, ProtocolConstants.DI_ROUTE_RECORD),
		new ABNFComponent(false,  0, -1, -1),
	};
	/**ABNF for ASA (section 8.5.2)*/
	public static final ABNFComponent abnf_asa[] = {
		new ABNFComponent(true,   1,  1, ProtocolConstants.DI_SESSION_ID),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_RESULT_CODE),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_HOST),
		new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_REALM),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_USER_NAME),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_ORIGIN_STATE_ID),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_ERROR_MESSAGE),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_ERROR_REPORTING_HOST),
		new ABNFComponent(false,  0, -1, ProtocolConstants.DI_FAILED_AVP),
		new ABNFComponent(false,  0, -1, ProtocolConstants.DI_REDIRECT_HOST),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_REDIRECT_HOST_USAGE),
		new ABNFComponent(false,  0,  1, ProtocolConstants.DI_REDIRECT_MAX_CACHE_TIME),
		new ABNFComponent(false,  0, -1, ProtocolConstants.DI_PROXY_INFO),
		new ABNFComponent(false,  0, -1, -1),
	};
	
	/** Result from {@link #checkABNF} */
	public static class CheckABNFFailure {
		/**A ready-made Failed-AVP*/
		public AVP failed_avp;
		/**Result-code*/
		public int result_code;
		/**Error-Message (can be null)*/
		public String error_message;
		public CheckABNFFailure(AVP avp, int result_code, String error_message) {
			this.failed_avp = avp;
			this.result_code = result_code;
			this.error_message = error_message;
		}
	};
	
	/**
	 * Check that a message conforms to an ABNF.
	 * The message is checked if it conforms to the ABNF specification.
	 * You can use it like this:
	 * <pre>
	 * public static final ABNFComponent abnf_my_message[] = {
	 *     new ABNFComponent(true,   1,  1, ProtocolConstants.DI_SESSION_ID),
	 *     new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_HOST),
	 *     new ABNFComponent(false,  1,  1, ProtocolConstants.DI_ORIGIN_REALM),
	 *     new ABNFComponent(false,  1,  1, ProtocolConstants.DI_USER_NAME),
	 *     ...
	 *     new ABNFComponent(false,  0, -1, -1),  //special marker for arbitrary AVPs
	 * };
	 * CheckABNFFailure caf = Utils.checkABNF(msg,abnf_my_message);
	 * if(caf!=null) {
	 *     <i>//The message did not conform to the ABNF</i>
	 *     Message response = new Message();
	 *     response.prepareResponse(msg);
	 *     ...
	 *     if(caf.failed_avp!=null)
	 *         msg.add(caf.failed_avp);
	 *     msg.add(new AVP_Unsigned32(ProtocolConstants.DI_RESULT_CODE,caf.result_code));
	 *     if(caf.error_message!=null)
	 *         msg.add(new AVP_UTF8String(ProtocolConstants.DI_ERROR_MESSAGE,caf.error_message));
		   ...
	 * }
	 * </pre>
	 * @param msg The message to be checked.
	 * @param abnf An array of ABNFComponents. You can use on of the predefined ones.
	 * @return Null on success. A CheckABNFFailure on failure.
	 */
	public static final CheckABNFFailure checkABNF(Message msg, ABNFComponent abnf[]) {
		boolean arbitrary_avps_allowed=false;
		for(int i=0; i<abnf.length; i++) {
			if(abnf[i].code==-1) {
				arbitrary_avps_allowed = true;
				break;
			}
		}
		for(int i=0; i<abnf.length; i++) {
			int code = abnf[i].code;
			if(code==-1) continue; //no checks on arbitrary AVPs
			if(abnf[i].min_count==0 && abnf[i].max_count==-1)
				continue; //no real limits.
			int count = msg.count(code);
			if(count<abnf[i].min_count) {
				//not present or too few occurrences
				return new CheckABNFFailure(new AVP(code,new byte[0]),
				                            ProtocolConstants.DIAMETER_RESULT_MISSING_AVP,
				                            (abnf[i].min_count==1)?null:("AVP must occur at least "+abnf[i].min_count+" times")
				                           );
			} else if(abnf[i].max_count!=-1 && count>abnf[i].max_count) {
				//locate the first violation
				AVP violating_avp=null;
				int j=0;
				for(AVP avp:msg.subset(code)) {
					j++;
					if(j>abnf[i].max_count) {
						violating_avp = avp;
						break;
					}
				}
				return new CheckABNFFailure(new AVP(violating_avp),
				                            ProtocolConstants.DIAMETER_RESULT_AVP_OCCURS_TOO_MANY_TIMES,
				                            null
				                           );
			}
			if(abnf[i].fixed_position) {
				//check position
				//assumption: previous AVPs are also fixed-position (otherwise this doesn't make much sense)
				//assumption: arbitrary AVPs are not allowed before this
				//todo: support previous AVP non-1 occurences
				int pos = msg.find_first(abnf[i].code);
				if(pos!=i) {
					//No really good result-code for this...
					return new CheckABNFFailure(new AVP(msg.get(pos)),
					                            ProtocolConstants.DIAMETER_RESULT_INVALID_AVP_VALUE,
					                            "AVP occurs at wrong position"
					                           );
				}
			}
		}
		if(!arbitrary_avps_allowed) {
			//check that there aren't any
			for(AVP avp:msg.avps()) {
				boolean allowed=false;
				for(ABNFComponent a:abnf) {
					if(avp.code==a.code) {
						allowed=true;
						break;
					}
				}
				if(!allowed) {
					return new CheckABNFFailure(new AVP(avp),
					                            ProtocolConstants.DIAMETER_RESULT_AVP_NOT_ALLOWED,
					                            null
					                           );
				}
			}
		}
		
		return null; //message passed checks
	}
}
