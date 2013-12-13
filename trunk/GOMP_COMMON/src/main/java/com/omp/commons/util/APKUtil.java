package com.omp.commons.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.res.AXmlResourceParser;
import android.util.TypedValue;

/**
 * APK Utility
 * @author pat
 *
 */
public class APKUtil {
	
	
	/**
	 * test method.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			APKInfomation apkInf	= getAPKInfomation(new File("E:\\user\\pat\\Desktop\\G9_Client_v3.57_20110621.apk"));
			
			System.out.println("     versionCode : " + apkInf.getVersionCode());
			System.out.println("     versionName : " + apkInf.getVersionName());
			System.out.println("         package : " + apkInf.getPackageName());
			System.out.println("   minSDKVersion : " + apkInf.getMinSDKVersion());
			System.out.println("targetSDKVersion : " + apkInf.getTargetSDKVersion());
			System.out.println("   maxSDKVersion : " + apkInf.getMaxSDKVersion());
			System.out.println(" usesPermissions : " + apkInf.getUsesPermissions());
			System.out.println("    usesFeatures : " + apkInf.getUsesFeatures());
			System.out.println("        isSigned : " + apkInf.isSigned());
			if (apkInf.isSigned()) {
				System.out.println("         isDebug : " + apkInf.isDebugSign());
				System.out.println("   validity days : " + apkInf.getSignKeyValidityDays());
				System.out.println("       signedKey : " + apkInf.getSignedKey());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * get APKInfomation form apkFile
	 * @param apkFile
	 * @return
	 * @throws IOException when apkfile read fail.
	 * @throws XmlPullParserException when AndroidManifest.xml parse fail.
	 */
    public static APKInfomation getAPKInfomation(File apkFile)
    	throws IOException, XmlPullParserException {
    	JarFile			jf;
    	APKInfomation	rv;
    	
    	rv	= new APKInfomation();
    	jf	= new JarFile(apkFile, true);
    	try {
    		JarEntry			je;
    		InputStream			is;
			AXmlResourceParser	parser;
			Certificate[]		cts;
			
			parser	= new AXmlResourceParser();
    		je		= jf.getJarEntry("AndroidManifest.xml");
    		if (je == null) {
    			throw new IOException("invalid apk file.");
    		}
    		is		= jf.getInputStream(je);
    		try {
    			parser.open(is);
    			try {
        			int				type;
        			List<String>	permList;
        			List<String>	featureList;
        			
        			permList	= new ArrayList<String>();
        			featureList	= new ArrayList<String>();
	    			type		= parser.next();
	    			while (type != XmlPullParser.END_DOCUMENT) {
	    				if (type == XmlPullParser.START_TAG) {
		    				String	tagName;
		    				int		attCount;
		    				
		    				tagName		= parser.getName();
		    				attCount	= parser.getAttributeCount();
		    				if ("manifest".equals(tagName)) {
		    					for (int i=0; i<attCount; i++) {
		    						String	attrName;
		    						
		    						attrName	= parser.getAttributeName(i);
		    						if ("versionCode".equals(attrName)) {
		    							rv.setVersionCode(getAttributeValue(parser, i));
		    						} else if ("versionName".equals(attrName)) {
		    							rv.setVersionName(getAttributeValue(parser, i));
		    						} else if ("package".equals(attrName)) {
		    							rv.setPackageName(getAttributeValue(parser, i));
		    						}
		    					}
		    				} else if ("uses-sdk".equals(tagName)) {
		    					for (int i=0; i<attCount; i++) {
		    						String	attrName;
		    						
		    						attrName	= parser.getAttributeName(i);
		    						if ("minSdkVersion".equals(attrName)) {
		    							rv.setMinSDKVersion(getAttributeValue(parser, i));
		    						} else if ("targetSdkVersion".equals(attrName)) {
		    							rv.setTargetSDKVersion(getAttributeValue(parser, i));
		    						} else if ("maxSdkVersion".equals(attrName)) {
		    							rv.setMaxSDKVersion(getAttributeValue(parser, i));
		    						}
		    					}
		    				} else if ("uses-permission".equals(tagName)) {
		    					for (int i=0; i<attCount; i++) {
		    						if ("name".equals(parser.getAttributeName(i))) {
		    							permList.add(getAttributeValue(parser, i));
		    						}
		    					}
		    				} else if ("uses-feature".equals(tagName)) {
		    					for (int i=0; i<attCount; i++) {
		    						if ("name".equals(parser.getAttributeName(i))) {
		    							featureList.add(getAttributeValue(parser, i));
		    						}
		    					}
		    				}
	    				}
	    				type	= parser.next();
	    			}
	    			rv.setUsesPermissions(permList.toArray(new String[permList.size()]));
	    			rv.setUsesFeatures(featureList.toArray(new String[featureList.size()]));
    			} finally {
    				parser.close();
    			}
    		} finally {
    			is.close();
    		}
    		cts	= je.getCertificates();
    		if (cts != null && cts[0] instanceof X509Certificate) {
    			rv.setSignedKey((X509Certificate)cts[0]);
    		}
    	} finally {
    		jf.close();
    	}
    	
		return rv;
    }
    
	private static String getAttributeValue(AXmlResourceParser parser,int index) {
		int type=parser.getAttributeValueType(index);
		int data=parser.getAttributeValueData(index);
		if (type==TypedValue.TYPE_STRING) {
			return parser.getAttributeValue(index);
		}
		if (type==TypedValue.TYPE_ATTRIBUTE) {
			return String.format("?%s%08X",getPackage(data),data);
		}
		if (type==TypedValue.TYPE_REFERENCE) {
			return String.format("@%s%08X",getPackage(data),data);
		}
		if (type==TypedValue.TYPE_FLOAT) {
			return String.valueOf(Float.intBitsToFloat(data));
		}
		if (type==TypedValue.TYPE_INT_HEX) {
			return String.format("0x%08X",data);
		}
		if (type==TypedValue.TYPE_INT_BOOLEAN) {
			return data!=0?"true":"false";
		}
		if (type==TypedValue.TYPE_DIMENSION) {
			return Float.toString(complexToFloat(data))+
				DIMENSION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
		}
		if (type==TypedValue.TYPE_FRACTION) {
			return Float.toString(complexToFloat(data))+
				FRACTION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
		}
		if (type>=TypedValue.TYPE_FIRST_COLOR_INT && type<=TypedValue.TYPE_LAST_COLOR_INT) {
			return String.format("#%08X",data);
		}
		if (type>=TypedValue.TYPE_FIRST_INT && type<=TypedValue.TYPE_LAST_INT) {
			return String.valueOf(data);
		}
		return String.format("<0x%X, type 0x%02X>",data,type);
	}
	
	private static String getPackage(int id) {
		if (id>>>24==1) {
			return "android:";
		}
		return "";
	}

	/////////////////////////////////// ILLEGAL STUFF, DONT LOOK :)
	
	public static float complexToFloat(int complex) {
		return (float)(complex & 0xFFFFFF00)*RADIX_MULTS[(complex>>4) & 3];
	}
	
	private static final float RADIX_MULTS[]={
		0.00390625F,3.051758E-005F,1.192093E-007F,4.656613E-010F
	};
	private static final String DIMENSION_UNITS[]={
		"px","dip","sp","pt","in","mm","",""
	};
	private static final String FRACTION_UNITS[]={
		"%","%p","","","","","",""
	};
    
	
}
