package dk.i1.diameter;

/**
 * A bunch of constants from RFC3588, RFC4005, RFC4006 and RFC4072.
 */
public final class ProtocolConstants {
	

	
	// ���ٽ����avp����
	//applications(myself)
	// �ͻ��˺ͷ����Ӧ�ã���Ϣ���Լ�AVP��
	static public final int DIAMETER_APPLICATION_NM = 6;
	// ��ȡ��������Ϣ��AVP
	static public final int DI_SEMNM_SWITCHES = 998;
	// ��ȡ������Ϣ��AVP
	static public final int DI_SEMNM_HOST     = 130;
	// ��ȡ��·��Ϣ��AVP
	static public final int DI_SEMNM_LINK     = 131;
	// ��ȡ��·������Ϣ��AVP
	static public final int DI_SEMNM_LINK_PER = 132;
	// ��ȡ�˿�������Ϣ��AVP
	static public final int DI_SEMNM_PORT_PER = 133;
	// ����
	static public final int DI_NM_REQ         = 466;
	// �ظ�
	static public final int DI_NM_RESP        = 467;
	
	static public final int NM_HELLO          = 999;
	// 定时执行任务，收集链路
	static public final int ONMP_LINK         = 468;
	
	

//Applications (section 2.4)
static public final int DIAMETER_APPLICATION_COMMON = 0;
static public final int DIAMETER_APPLICATION_NASREQ = 1;
static public final int DIAMETER_APPLICATION_MOBILEIP = 2;
static public final int DIAMETER_APPLICATION_ACCOUNTING = 3;
static public final int DIAMETER_APPLICATION_RELAY = 0xffffffff;

//message codes (section 3.1)
static public final int DIAMETER_COMMAND_CAPABILITIES_EXCHANGE = 257;
static public final int DIAMETER_COMMAND_REAUTH                = 258;
static public final int DIAMETER_COMMAND_ACCOUNTING            = 271;
static public final int DIAMETER_COMMAND_ABORT_SESSION         = 274;
static public final int DIAMETER_COMMAND_SESSION_TERMINATION   = 275;
static public final int DIAMETER_COMMAND_DEVICE_WATCHDOG       = 280;
static public final int DIAMETER_COMMAND_DISCONNECT_PEER       = 282;

//AVP codes
static public final int DI_USER_NAME                          = 1;
static public final int DI_CLASS                              = 25;
static public final int DI_SESSION_TIMEOUT                    = 27;
static public final int DI_PROXY_STATE                        = 33;
static public final int DI_ACCOUNTING_SESSION_ID              = 44;
static public final int DI_ACCT_MULTI_SESSION_ID              = 50;
static public final int DI_EVENT_TIMESTAMP                    = 55;
static public final int DI_ACCT_INTERIM_INTERVAL              = 85;
static public final int DI_HOST_IP_ADDRESS                    = 257;
static public final int DI_AUTH_APPLICATION_ID                = 258;
static public final int DI_ACCT_APPLICATION_ID                = 259;
static public final int DI_VENDOR_SPECIFIC_APPLICATION_ID     = 260;
static public final int DI_REDIRECT_HOST_USAGE                = 261;
static public final int DI_REDIRECT_MAX_CACHE_TIME            = 262;
static public final int DI_SESSION_ID                         = 263;
static public final int DI_ORIGIN_HOST                        = 264;
static public final int DI_SUPPORTED_VENDOR_ID                = 265;
static public final int DI_VENDOR_ID                          = 266;
static public final int DI_FIRMWARE_REVISION                  = 267;
static public final int DI_RESULT_CODE                        = 268;
static public final int DI_PRODUCT_NAME                       = 269;
static public final int DI_SESSION_BINDING                    = 270;
static public final int DI_SESSION_SERVER_FAILOVER            = 271;
static public final int DI_MULTI_ROUND_TIME_OUT               = 272;
static public final int DI_DISCONNECT_CAUSE                   = 273;
static public final int DI_AUTH_REQUEST_TYPE                  = 274;
static public final int DI_AUTH_GRACE_PERIOD                  = 276;
static public final int DI_AUTH_SESSION_STATE                 = 277;
static public final int DI_ORIGIN_STATE_ID                    = 278;
static public final int DI_FAILED_AVP                         = 279;
static public final int DI_PROXY_HOST                         = 280;
static public final int DI_ERROR_MESSAGE                      = 281;
static public final int DI_ROUTE_RECORD                       = 282;
static public final int DI_DESTINATION_REALM                  = 283;
static public final int DI_PROXY_INFO                         = 284;
static public final int DI_RE_AUTH_REQUEST_TYPE               = 285;
static public final int DI_ACCOUNTING_SUB_SESSION_ID          = 287;
static public final int DI_AUTHORIZATION_LIFETIME             = 291;
static public final int DI_REDIRECT_HOST                      = 292;
static public final int DI_DESTINATION_HOST                   = 293;
static public final int DI_ERROR_REPORTING_HOST               = 294;
static public final int DI_TERMINATION_CAUSE                  = 295;
static public final int DI_ORIGIN_REALM                       = 296;
static public final int DI_EXPERIMENTAL_RESULT                = 297;
static public final int DI_EXPERIMENTAL_RESULT_CODE           = 298;
static public final int DI_INBAND_SECURITY_ID                 = 299;
static public final int DI_E2E_SEQUENCE_AVP                   = 300;
static public final int DI_ACCOUNTING_RECORD_TYPE             = 480;
static public final int DI_ACCOUNTING_REALTIME_REQUIRED       = 483;
static public final int DI_ACCOUNTING_RECORD_NUMBER           = 485;


//enum for DI_DISCONNECT_CAUSE (section 5.4.3)
static public final int DI_DISCONNECT_CAUSE_REBOOTING = 0;
static public final int DI_DISCONNECT_CAUSE_BUSY = 1;
static public final int DI_DISCONNECT_CAUSE_DO_NOT_WANT_TO_TALK_TO_YOU = 2;

//enum for DI_REDIRECT_HOST_USAGE
static public final int DI_REDIRECT_HOST_USAGE_DONT_CACHE = 0;
static public final int DI_REDIRECT_HOST_USAGE_ALL_SESSION = 1;
static public final int DI_REDIRECT_HOST_USAGE_ALL_REALM = 2;
static public final int DI_REDIRECT_HOST_USAGE_REALM_AND_APPLICATION = 3;
static public final int DI_REDIRECT_HOST_USAGE_ALL_APPLICATION = 4;
static public final int DI_REDIRECT_HOST_USAGE_ALL_HOST = 5;
static public final int DI_REDIRECT_HOST_USAGE_ALL_USER = 6;

//enum for DI_INBAND_SECURITY_ID (section 6.10)
static public final int DI_INBAND_SECURITY_ID_NO_INBAND_SECURITY = 0;
static public final int DI_INBAND_SECURITY_ID_TLS = 1;

//enum for DI_AUTH_REQUEST_TYPE (section 8.7)
static public final int DI_AUTH_REQUEST_TYPE_AUTHENTICATE_ONLY = 1;
static public final int DI_AUTH_REQUEST_TYPE_AUTHORIZE_ONLY = 2;
static public final int DI_AUTH_REQUEST_TYPE_AUTHENTICATE = 3;

//enum for DI_AUTH_SESSION_STATE
static public final int DI_AUTH_SESSION_STATE_STALE_MAINTAINED = 0;
static public final int DI_AUTH_SESSION_STATE_NO_STALE_MAINTAINED = 1;

//enum for DI_RE_AUTH_REQUEST_TYPE
static public final int DI_RE_AUTH_REQUEST_TYPE_AUTHORIZE_ONLY = 0;
static public final int DI_RE_AUTH_REQUEST_TYPE_AUTHORIZE_AUTHENTICATE = 1;

//enum for DI_TERMINATION_CAUSE (section 8.15)
static public final int DI_TERMINATION_CAUSE_DIAMETER_LOGOUT                 = 1;
static public final int DI_TERMINATION_CAUSE_DIAMETER_SERVICE_NOT_PROVIDED   = 2;
static public final int DI_TERMINATION_CAUSE_DIAMETER_BAD_ANSWER             = 3;
static public final int DI_TERMINATION_CAUSE_DIAMETER_ADMINISTRATIVE         = 4;
static public final int DI_TERMINATION_CAUSE_DIAMETER_LINK_BROKEN            = 5;
static public final int DI_TERMINATION_CAUSE_DIAMETER_AUTH_EXPIRED           = 6;
static public final int DI_TERMINATION_CAUSE_DIAMETER_USER_MOVED             = 7;
static public final int DI_TERMINATION_CAUSE_DIAMETER_SESSION_TIMEOUT        = 8;

//bit flags for DI_SESSION_BINDING (section 8.17)
static public final int DI_SESSION_BINDING_RE_AUTH    = 0x0001;
static public final int DI_SESSION_BINDING_STR        = 0x0002;
static public final int DI_SESSION_BINDING_ACCOUNTING = 0x0004;

//enum for DI_SESSION_SERVER_FAILOVER (section 8.18)
static public final int DI_SESSION_SERVER_FAILOVER_REFUSE_SERVICE = 0;
static public final int DI_SESSION_SERVER_FAILOVER_TRY_AGAIN = 1;
static public final int DI_SESSION_SERVER_FAILOVER_ALLOW_SERVICE = 2;
static public final int DI_SESSION_SERVER_FAILOVER_TRY_AGAIN_ALLOW_SERVICE = 3;

//enum for DI_ACCOUNTING_RECORD_TYPE (section 9.8.1)
static public final int DI_ACCOUNTING_RECORD_TYPE_EVENT_RECORD = 1;
static public final int DI_ACCOUNTING_RECORD_TYPE_START_RECORD = 2;
static public final int DI_ACCOUNTING_RECORD_TYPE_INTERIM_RECORD = 3;
static public final int DI_ACCOUNTING_RECORD_TYPE_STOP_RECORD = 4;

//enum for DI_ACCOUNTING_REALTIME_REQUIRED (section 9.8.7)
static public final int DI_ACCOUNTING_REALTIME_REQUIRED_DELIVER_AND_GRANT = 1;
static public final int DI_ACCOUNTING_REALTIME_REQUIRED_GRANT_AND_STORE = 2;
static public final int DI_ACCOUNTING_REALTIME_REQUIRED_GRANT_AND_LOSE = 3;

//result codes (enum for result-code AVP)
static public final int DIAMETER_RESULT_MULTI_ROUND_AUTH         = 1001;
static public final int DIAMETER_RESULT_SUCCESS                  = 2001;
static public final int DIAMETER_RESULT_LIMITED_SUCCESS          = 2002;
static public final int DIAMETER_RESULT_COMMAND_UNSUPPORTED      = 3001;
static public final int DIAMETER_RESULT_UNABLE_TO_DELIVER        = 3002;
static public final int DIAMETER_RESULT_REALM_NOT_SERVED         = 3003;
static public final int DIAMETER_RESULT_TOO_BUSY                 = 3004;
static public final int DIAMETER_RESULT_LOOP_DETECTED            = 3005;
static public final int DIAMETER_RESULT_REDIRECT_INDICATION      = 3006;
static public final int DIAMETER_RESULT_APPLICATION_UNSUPPORTED  = 3007;
static public final int DIAMETER_RESULT_INVALID_HDR_BITS         = 3008;
static public final int DIAMETER_RESULT_INVALID_AVP_BITS         = 3009;
static public final int DIAMETER_RESULT_UNKNOWN_PEER             = 3010;
static public final int DIAMETER_RESULT_AUTHENTICATION_REJECTED  = 4001;
static public final int DIAMETER_RESULT_OUT_OF_SPACE             = 4002;
static public final int DIAMETER_RESULT_ELECTION_LOST            = 4003; //official name: ELECTION_LOST
static public final int DIAMETER_RESULT_AVP_UNSUPPORTED          = 5001;
static public final int DIAMETER_RESULT_UNKNOWN_SESSION_ID       = 5002;
static public final int DIAMETER_RESULT_AUTHORIZATION_REJECTED   = 5003;
static public final int DIAMETER_RESULT_INVALID_AVP_VALUE        = 5004;
static public final int DIAMETER_RESULT_MISSING_AVP              = 5005;
static public final int DIAMETER_RESULT_RESOURCES_EXCEEDED       = 5006;
static public final int DIAMETER_RESULT_CONTRADICTING_AVPS       = 5007;
static public final int DIAMETER_RESULT_AVP_NOT_ALLOWED          = 5008;
static public final int DIAMETER_RESULT_AVP_OCCURS_TOO_MANY_TIMES= 5009;
static public final int DIAMETER_RESULT_NO_COMMON_APPLICATION    = 5010;
static public final int DIAMETER_RESULT_UNSUPPORTED_VERSION      = 5011;
static public final int DIAMETER_RESULT_UNABLE_TO_COMPLY         = 5012;
static public final int DIAMETER_RESULT_INVALID_BIT_IN_HEADER    = 5013;
static public final int DIAMETER_RESULT_INVALID_AVP_LENGTH       = 5014;
static public final int DIAMETER_RESULT_INVALID_MESSAGE_LENGTH   = 5015;
static public final int DIAMETER_RESULT_INVALID_AVP_BIT_COMBO    = 5016;
static public final int DIAMETER_RESULT_NO_COMMON_SECURITY       = 5017;

//RFC4005 - Diameter Network Access Server Application
static public final int DIAMETER_COMMAND_AA                    = 265;

//AVPs (section 4.1)
static public final int DI_NAS_PORT                              = 5;
static public final int DI_NAS_PORT_ID                           = 87;
static public final int DI_NAS_PORT_TYPE                         = 61;
static public final int DI_CALLED_STATION_ID                     = 30;
static public final int DI_CALLING_STATION_ID                    = 31;
static public final int DI_CONNECT_INFO                          = 77;
static public final int DI_ORIGINATING_LINE_INFO                 = 94;
static public final int DI_REPLY_MESSAGE                         = 18;

//AVPs (section 5)
static public final int DI_USER_PASSWORD                         = 2;
static public final int DI_PASSWORD_RETRY                        = 75;
static public final int DI_PROMPT                                = 76;
static public final int DI_CHAP_AUTH                             = 402;
static public final int DI_CHAP_ALGORITHM                        = 403;
static public final int DI_CHAP_IDENT                            = 404;
static public final int DI_CHAP_RESPONSE                         = 405;
static public final int DI_CHAP_CHALLENGE                        = 60;
static public final int DI_ARAP_PASSWORD                         = 70;
static public final int DI_ARAP_CHALLENGE_RESPONSE               = 84;
static public final int DI_ARAP_SECURITY                         = 73;
static public final int DI_ARAP_SECURITY_DATA                    = 74;

//AVPs (section 6)
static public final int DI_SERVICE_TYPE                          = 6;
static public final int DI_CALLBACK_NUMBER                       = 19;
static public final int DI_CALLBACK_ID                           = 20;
static public final int DI_IDLE_TIMEOUT                          = 28;
static public final int DI_PORT_LIMIT                            = 62;
static public final int DI_NAS_FILTER_RULE                       = 400;
static public final int DI_FILTER_ID                             = 11;
static public final int DI_CONFIGURATION_TOKEN                   = 78;
static public final int DI_QOS_FILTER_RULE                       = 407;
static public final int DI_FRAMED_PROTOCOL                       = 7;
static public final int DI_FRAMED_ROUTING                        = 10;
static public final int DI_FRAMED_MTU                            = 12;
static public final int DI_FRAMED_COMPRESSION                    = 13;
static public final int DI_FRAMED_IP_ADDRESS                     = 8;
static public final int DI_FRAMED_IP_NETMASK                     = 9;
static public final int DI_FRAMED_ROUTE                          = 22;
static public final int DI_FRAMED_POOL                           = 88;
static public final int DI_FRAMED_INTERFACE_ID                   = 96;
static public final int DI_FRAMED_IPV6_PREFIX                    = 97;
static public final int DI_FRAMED_IPV6_ROUTE                     = 99;
static public final int DI_FRAMED_IPV6_POOL                      = 100;
static public final int DI_FRAMED_IPX_NETWORK                    = 23;
static public final int DI_FRAMED_APPLETALK_LINK                 = 37;
static public final int DI_FRAMED_APPLETALK_NETWORK              = 38;
static public final int DI_FRAMED_APPLETALK_ZONE                 = 39;
static public final int DI_ARAP_FEATURES                         = 71;
static public final int DI_ARAP_ZONE_ACCESS                      = 72;
static public final int DI_LOGIN_IP_HOST                         = 14;
static public final int DI_LOGIN_IPV6_HOST                       = 98;
static public final int DI_LOGIN_SERVICE                         = 15;
static public final int DI_LOGIN_TCP_PORT                        = 16;
static public final int DI_LOGIN_LAT_SERVICE                     = 34;
static public final int DI_LOGIN_LAT_NODE                        = 35;
static public final int DI_LOGIN_LAT_GROUP                       = 36;
static public final int DI_LOGIN_LAT_PORT                        = 63;

//AVPs (section 7)
static public final int DI_TUNNELING                             = 401;
static public final int DI_TUNNEL_TYPE                           = 64;
static public final int DI_TUNNEL_MEDIUM_TYPE                    = 65;
static public final int DI_TUNNEL_CLIENT_ENDPOINT                = 66;
static public final int DI_TUNNEL_SERVER_ENDPOINT                = 67;
static public final int DI_TUNNEL_PASSWORD                       = 69;
static public final int DI_TUNNEL_PRIVATE_GROUP_ID               = 81;
static public final int DI_TUNNEL_ASSIGNMENT_ID                  = 82;
static public final int DI_TUNNEL_PREFERENCE                     = 83;
static public final int DI_TUNNEL_CLIENT_AUTH_ID                 = 90;
static public final int DI_TUNNEL_SERVER_AUTH_ID                 = 91;

//AVPs (section 8)
static public final int DI_ACCOUNTING_INPUT_OCTETS               = 363;
static public final int DI_ACCOUNTING_OUTPUT_OCTETS              = 364;
static public final int DI_ACCOUNTING_INPUT_PACKETS              = 365;
static public final int DI_ACCOUNTING_OUTPUT_PACKETS             = 366;
static public final int DI_ACCT_SESSION_TIME                     = 46;
static public final int DI_ACCT_AUTHENTIC                        = 45;
static public final int DI_ACOUNTING_AUTH_METHOD                 = 406;
static public final int DI_ACCT_DELAY_TIME                       = 41;
static public final int DI_ACCT_LINK_COUNT                       = 51;
static public final int DI_ACCT_TUNNEL_CONNECTION                = 68;
static public final int DI_ACCT_TUNNEL_PACKETS_LOST              = 86;


//=============================================================================
//RFC4006 Credit Control application

//Applications (section 1.3)
static public final int DIAMETER_APPLICATION_CREDIT_CONTROL = 4;

//Message codes (section 3)
static public final int DIAMETER_COMMAND_CC                    = 272;

//AVPs (section 8)
static public final int DI_CC_CORRELATION_ID                  = 411;
static public final int DI_CC_INPUT_OCTETS                    = 412;
static public final int DI_CC_MONEY                           = 413;
static public final int DI_CC_OUTPUT_OCTETS                   = 414;
static public final int DI_CC_REQUEST_NUMBER                  = 415;
static public final int DI_CC_REQUEST_TYPE                    = 416;
static public final int DI_CC_SERVICE_SPECIFIC_UNITS          = 417;
static public final int DI_CC_SESSION_FAILOVER                = 418;
static public final int DI_CC_SUB_SESSION_ID                  = 419;
static public final int DI_CC_TIME                            = 420;
static public final int DI_CC_TOTAL_OCTETS                    = 421;
static public final int DI_CC_UNIT_TYPE                       = 454;
static public final int DI_CHECK_BALANCE_RESULT               = 422;
static public final int DI_COST_INFORMATION                   = 423;
static public final int DI_COST_UNIT                          = 424;
static public final int DI_CREDIT_CONTROL                     = 426;
static public final int DI_CREDIT_CONTROL_FAILURE_HANDLING    = 427;
static public final int DI_CURRENCY_CODE                      = 425;
static public final int DI_DIRECT_DEBITING_FAILURE_HANDLING   = 428;
static public final int DI_EXPONENT                           = 429;
static public final int DI_FINAL_UNIT_ACTION                  = 449;
static public final int DI_FINAL_UNIT_INDICATION              = 430;
static public final int DI_GRANTED_SERVICE_UNIT               = 431;
static public final int DI_G_S_U_POOL_IDENTIFIER              = 453;
static public final int DI_G_S_U_POOL_REFERENCE               = 457;
static public final int DI_MULTIPLE_SERVICES_CREDIT_CONTROL   = 456;
static public final int DI_MULTIPLE_SERVICES_INDICATOR        = 455;
static public final int DI_RATING_GROUP                       = 432;
static public final int DI_REDIRECT_ADDRESS_TYPE              = 433;
static public final int DI_REDIRECT_SERVER                    = 434;
static public final int DI_REDIRECT_SERVER_ADDRESS            = 435;
static public final int DI_REQUESTED_ACTION                   = 436;
static public final int DI_REQUESTED_SERVICE_UNIT             = 437;
static public final int DI_RESTRICTION_FILTER_RULE            = 438;
static public final int DI_SERVICE_CONTEXT_ID                 = 461;
static public final int DI_SERVICE_IDENTIFIER                 = 439;
static public final int DI_SERVICE_PARAMETER_INFO             = 440;
static public final int DI_SERVICE_PARAMETER_TYPE             = 441;
static public final int DI_SERVICE_PARAMETER_VALUE            = 442;
static public final int DI_SUBSCRIPTION_ID                    = 443;
static public final int DI_SUBSCRIPTION_ID_DATA               = 444;
static public final int DI_SUBSCRIPTION_ID_TYPE               = 450;
static public final int DI_TARIFF_CHANGE_USAGE                = 452;
static public final int DI_TARIFF_TIME_CHANGE                 = 451;
static public final int DI_UNIT_VALUE                         = 445;
static public final int DI_USED_SERVICE_UNIT                  = 446;
static public final int DI_USER_EQUIPMENT_INFO                = 458;
static public final int DI_USER_EQUIPMENT_INFO_TYPE           = 459;
static public final int DI_USER_EQUIPMENT_INFO_VALUE          = 460;
static public final int DI_VALUE_DIGITS                       = 447;
static public final int DI_VALIDITY_TIME                      = 448;

//enum for CC-Request-Type
static public final int DI_CC_REQUEST_TYPE_INITIAL_REQUEST              = 1;
static public final int DI_CC_REQUEST_TYPE_UPDATE_REQUEST               = 2;
static public final int DI_CC_REQUEST_TYPE_TERMINATION_REQUEST          = 3;
static public final int DI_CC_REQUEST_TYPE_EVENT_REQUEST                = 4;
//enum for CC-Session-Failover
static public final int DI_CC_SESSION_FAILOVER_FAILOVER_NOT_SUPPORTED   = 0;
static public final int DI_CC_SESSION_FAILOVER_FAILOVER_SUPPORTED       = 1;
//enum for Check-Balance-Result
static public final int DI_DI_CHECK_BALANCE_RESULT_ENOUGH_CREDIT        = 0;
static public final int DI_DI_CHECK_BALANCE_RESULT_NO_CREDIT            = 1;
//enum for Credit-Control
static public final int DI_DI_CREDIT_CONTROL_CREDIT_AUTHORIZATION       = 0;
static public final int DI_DI_CREDIT_CONTROL_RE_AUTHORIZATION           = 1;
//enum for Credit-Control-Failure-Handling
static public final int DI_CREDIT_CONTROL_FAILURE_HANDLING_TERMINATE           = 0;
static public final int DI_CREDIT_CONTROL_FAILURE_HANDLING_CONTINUE            = 1;
static public final int DI_CREDIT_CONTROL_FAILURE_HANDLING_RETRY_AND_TERMINATE = 2;
//enum for Direct-Debiting-Failure-Handling
static public final int DI_DIRECT_DEBITING_FAILURE_HANDLING_TERMINATE_OR_BUFFER = 0;
static public final int DI_DIRECT_DEBITING_FAILURE_HANDLING_CONTINUE            = 1;
//enum for Tariff-Change-Usage
static public final int DI_TARIFF_CHANGE_USAGE_UNIT_BEFORE_TARIFF_CHANGE = 0;
static public final int DI_TARIFF_CHANGE_USAGE_UNIT_AFTER_TARIFF_CHANGE  = 1;
static public final int DI_TARIFF_CHANGE_USAGE_UNIT_INDETERMINATE        = 2;
//enum for CC-Unit-Type
static public final int DI_CC_UNIT_TYPE_TIME                            = 0;
static public final int DI_CC_UNIT_TYPE_MONEY                           = 1;
static public final int DI_CC_UNIT_TYPE_TOTAL_OCTETS                    = 2;
static public final int DI_CC_UNIT_TYPE_INPUT_OCTETS                    = 3;
static public final int DI_CC_UNIT_TYPE_OUTPUT_OCTETS                   = 4;
static public final int DI_CC_UNIT_TYPE_SERVICE_SPECIFIC_UNITS          = 5;
//enum for Final-Unit-Action
static public final int DI_FINAL_UNIT_ACTION_TERMINATE                  = 0;
static public final int DI_FINAL_UNIT_ACTION_REDIRECT                   = 1;
static public final int DI_FINAL_UNIT_ACTION_RESTRICT_ACCESS            = 2;
//enum for Redirect-Address-Type
static public final int DI_REDIRECT_ADDRESS_TYPE_IPV4_ADDRESS           = 0;
static public final int DI_REDIRECT_ADDRESS_TYPE_IPV6_ADDRESS           = 1;
static public final int DI_REDIRECT_ADDRESS_TYPE_URL                    = 2;
static public final int DI_REDIRECT_ADDRESS_TYPE_SIP_URL                = 3;
//enum for Multiple-Services-Indicator
static public final int DI_MULTIPLE_SERVICES_INDICATOR_MULTIPLE_SERVICES_NOT_SUPPORTED = 0;
static public final int DI_MULTIPLE_SERVICES_INDICATOR_MULTIPLE_SERVICES_SUPPORTED     = 1;
//enum for Requested-Action
static public final int DI_REQUESTED_ACTION_DIRECT_DEBITING             = 0;
static public final int DI_REQUESTED_ACTION_REFUND_ACCOUNT              = 1;
static public final int DI_REQUESTED_ACTION_CHECK_BALANCE               = 2;
static public final int DI_REQUESTED_ACTION_PRICE_ENQUIRY               = 3;
//enum for Subscription-Id-Type
static public final int DI_SUBSCRIPTION_ID_TYPE_END_USER_E164           = 0;
static public final int DI_SUBSCRIPTION_ID_TYPE_END_USER_IMSI           = 1;
static public final int DI_SUBSCRIPTION_ID_TYPE_END_USER_SIP_URI        = 2;
static public final int DI_SUBSCRIPTION_ID_TYPE_END_USER_NAI            = 3;
static public final int DI_SUBSCRIPTION_ID_TYPE_END_USER_PRIVATE        = 4;
//enum for User-Equipment-Info-Type
static public final int DI_USER_EQUIPMENT_INFO_TYPE_IMEISV              = 0;
static public final int DI_USER_EQUIPMENT_INFO_TYPE_MAC                 = 1;
static public final int DI_USER_EQUIPMENT_INFO_TYPE_EUI64               = 2;
static public final int DI_USER_EQUIPMENT_INFO_TYPE_MODIFIED_EUI64      = 3;

//Result codes
static public final int DIAMETER_RESULT_END_USER_SERVICE_DENIED         = 4010;
static public final int DIAMETER_RESULT_CREDIT_CONTROL_NOT_APPLICABLE   = 4011;
static public final int DIAMETER_RESULT_CREDIT_LIMIT_REACHED            = 4012;
static public final int DIAMETER_RESULT_USER_UNKNOWN                    = 5030;
static public final int DIAMETER_RESULT_RATING_FAILED                   = 5031;


//=============================================================================
//RFC 4072 Diameter EAP Application
//applications (section 2.1)
static public final int DIAMETER_APPLICATION_EAP = 5;

//message codes (section 3)
static public final int DIAMETER_COMMAND_EAP = 268;

//AVPs (section 4.1)
static public final int DI_EAP_PAYLOAD                           = 462;
static public final int DI_EAP_REISSUED_PAYLOAD                  = 463;
static public final int DI_EAP_MASTER_SESSION_KEY                = 464;
static public final int DI_EAP_KEY_NAME                          = 102;
static public final int DI_ACCOUNTING_EAP_AUTH_METHOD            = 465;



}
