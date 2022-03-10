package com.gsd.sreenidhi.mail;

public class EmailForm {
	private String emailTemplate = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
			"\r\n" + 
			"<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\r\n" + 
			"<head>\r\n" + 
			"<!--[if gte mso 9]><xml><o:OfficeDocumentSettings><o:AllowPNG/><o:PixelsPerInch>96</o:PixelsPerInch></o:OfficeDocumentSettings></xml><![endif]-->\r\n" + 
			"<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\r\n" + 
			"<meta content=\"width=device-width\" name=\"viewport\"/>\r\n" + 
			"<!--[if !mso]><!-->\r\n" + 
			"<meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\"/>\r\n" + 
			"<!--<![endif]-->\r\n" + 
			"<title></title>\r\n" + 
			"<!--[if !mso]><!-->\r\n" + 
			"<link href=\"https://fonts.googleapis.com/css?family=Montserrat\" rel=\"stylesheet\" type=\"text/css\"/>\r\n" + 
			"<link href=\"https://fonts.googleapis.com/css?family=Open+Sans\" rel=\"stylesheet\" type=\"text/css\"/>\r\n" + 
			"<!--<![endif]-->\r\n" + 
			"<style type=\"text/css\">\r\n" + 
			"		body {\r\n" + 
			"			margin: 0;\r\n" + 
			"			padding: 0;\r\n" + 
			"		}\r\n" + 
			"\r\n" + 
			"		table,\r\n" + 
			"		td,\r\n" + 
			"		tr {\r\n" + 
			"			vertical-align: top;\r\n" + 
			"			border-collapse: collapse;\r\n" + 
			"		}\r\n" + 
			"\r\n" + 
			"		* {\r\n" + 
			"			line-height: inherit;\r\n" + 
			"		}\r\n" + 
			"\r\n" + 
			"		a[x-apple-data-detectors=true] {\r\n" + 
			"			color: inherit !important;\r\n" + 
			"			text-decoration: none !important;\r\n" + 
			"		}\r\n" + 
			"	</style>\r\n" + 
			"<style id=\"media-query\" type=\"text/css\">\r\n" + 
			"		@media (max-width: 620px) {\r\n" + 
			"\r\n" + 
			"			.block-grid,\r\n" + 
			"			.col {\r\n" + 
			"				min-width: 320px !important;\r\n" + 
			"				max-width: 100% !important;\r\n" + 
			"				display: block !important;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			.block-grid {\r\n" + 
			"				width: 100% !important;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			.col {\r\n" + 
			"				width: 100% !important;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			.col>div {\r\n" + 
			"				margin: 0 auto;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			img.fullwidth,\r\n" + 
			"			img.fullwidthOnMobile {\r\n" + 
			"				max-width: 100% !important;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			.no-stack .col {\r\n" + 
			"				min-width: 0 !important;\r\n" + 
			"				display: table-cell !important;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			.no-stack.two-up .col {\r\n" + 
			"				width: 50% !important;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			.no-stack .col.num4 {\r\n" + 
			"				width: 33% !important;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			.no-stack .col.num8 {\r\n" + 
			"				width: 66% !important;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			.no-stack .col.num4 {\r\n" + 
			"				width: 33% !important;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			.no-stack .col.num3 {\r\n" + 
			"				width: 25% !important;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			.no-stack .col.num6 {\r\n" + 
			"				width: 50% !important;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			.no-stack .col.num9 {\r\n" + 
			"				width: 75% !important;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			.video-block {\r\n" + 
			"				max-width: none !important;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			.mobile_hide {\r\n" + 
			"				min-height: 0px;\r\n" + 
			"				max-height: 0px;\r\n" + 
			"				max-width: 0px;\r\n" + 
			"				display: none;\r\n" + 
			"				overflow: hidden;\r\n" + 
			"				font-size: 0px;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			.desktop_hide {\r\n" + 
			"				display: block !important;\r\n" + 
			"				max-height: none !important;\r\n" + 
			"			}\r\n" + 
			"		}\r\n" + 
			"	</style>\r\n" + 
			"<style id=\"menu-media-query\" type=\"text/css\">\r\n" + 
			"		@media (max-width: 620px) {\r\n" + 
			"			.menu-checkbox[type=\"checkbox\"]~.menu-links {\r\n" + 
			"				display: none !important;\r\n" + 
			"				padding: 5px 0;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			.menu-checkbox[type=\"checkbox\"]~.menu-links span.sep {\r\n" + 
			"				display: none;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			.menu-checkbox[type=\"checkbox\"]:checked~.menu-links,\r\n" + 
			"			.menu-checkbox[type=\"checkbox\"]~.menu-trigger {\r\n" + 
			"				display: block !important;\r\n" + 
			"				max-width: none !important;\r\n" + 
			"				max-height: none !important;\r\n" + 
			"				font-size: inherit !important;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			.menu-checkbox[type=\"checkbox\"]~.menu-links>a,\r\n" + 
			"			.menu-checkbox[type=\"checkbox\"]~.menu-links>span.label {\r\n" + 
			"				display: block !important;\r\n" + 
			"				text-align: center;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			.menu-checkbox[type=\"checkbox\"]:checked~.menu-trigger .menu-close {\r\n" + 
			"				display: block !important;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			.menu-checkbox[type=\"checkbox\"]:checked~.menu-trigger .menu-open {\r\n" + 
			"				display: none !important;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			#menucsyg7v~div label {\r\n" + 
			"				border-radius: 0% !important;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			#menucsyg7v:checked~.menu-links {\r\n" + 
			"				background-color: #000000 !important;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			#menucsyg7v:checked~.menu-links a {\r\n" + 
			"				color: #ffffff !important;\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			#menucsyg7v:checked~.menu-links span {\r\n" + 
			"				color: #ffffff !important;\r\n" + 
			"			}\r\n" + 
			"		}\r\n" + 
			"	</style>\r\n" + 
			"</head>\r\n" + 
			"<body class=\"clean-body\" style=\"margin: 0; padding: 0; -webkit-text-size-adjust: 100%; background-color: #ededed;\">\r\n" + 
			"<!--[if IE]><div class=\"ie-browser\"><![endif]-->\r\n" + 
			"<table bgcolor=\"#ededed\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; min-width: 320px; Margin: 0 auto; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ededed; width: 100%;\" valign=\"top\" width=\"100%\">\r\n" + 
			"<tbody>\r\n" + 
			"<tr style=\"vertical-align: top;\" valign=\"top\">\r\n" + 
			"<td style=\"word-break: break-word; vertical-align: top;\" valign=\"top\">\r\n" + 
			"<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color:#ededed\"><![endif]-->\r\n" + 
			"<div style=\"background-color:transparent;\">\r\n" + 
			"<div class=\"block-grid\" style=\"Margin: 0 auto; min-width: 320px; max-width: 600px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: #8b3de4;\">\r\n" + 
			"<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#8b3de4;background-image:url('https://blinqlabs.com/resources/email/images/header_bg.png');background-position:center top;background-repeat:no-repeat\">\r\n" + 
			"<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px\"><tr class=\"layout-full-width\" style=\"background-color:#8b3de4\"><![endif]-->\r\n" + 
			"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\";width:600px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\r\n" + 
			"<div class=\"col num12\" style=\"min-width: 320px; max-width: 600px; display: table-cell; vertical-align: top; width: 600px;\">\r\n" + 
			"<div style=\"width:100% !important;\">\r\n" + 
			"<!--[if (!mso)&(!IE)]><!-->\r\n" + 
			"<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\r\n" + 
			"<!--<![endif]-->\r\n" + 
			"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\r\n" + 
			"<tbody>\r\n" + 
			"<tr style=\"vertical-align: top;\" valign=\"top\">\r\n" + 
			"<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\r\n" + 
			"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"15\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 15px; width: 100%;\" valign=\"top\" width=\"100%\">\r\n" + 
			"<tbody>\r\n" + 
			"<tr style=\"vertical-align: top;\" valign=\"top\">\r\n" + 
			"<td height=\"15\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\r\n" + 
			"</tr>\r\n" + 
			"</tbody>\r\n" + 
			"</table>\r\n" + 
			"</td>\r\n" + 
			"</tr>\r\n" + 
			"</tbody>\r\n" + 
			"</table>\r\n" + 
			"<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 35px; padding-left: 35px; padding-top: 10px; padding-bottom: 5px; font-family: 'Trebuchet MS', Tahoma, sans-serif\"><![endif]-->\r\n" + 
			"<div style=\"color:#ffffff;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:35px;padding-bottom:5px;padding-left:35px;\">\r\n" + 
			"<div style=\"line-height: 1.2; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #ffffff; mso-line-height-alt: 14px;\">\r\n" + 
			"<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 17px; margin: 0;\"><strong><span style=\"font-size: 12px;\">Hello ${username}</span></strong></p>\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"<!--[if mso]></td></tr></table><![endif]-->\r\n" + 
			"<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 35px; padding-left: 35px; padding-top: 5px; padding-bottom: 10px; font-family: Tahoma, sans-serif\"><![endif]-->\r\n" + 
			"<div style=\"color:#ffffff;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:5px;padding-right:35px;padding-bottom:10px;padding-left:35px;\">\r\n" + 
			"<div style=\"line-height: 1.2; font-size: 12px; color: #ffffff; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\r\n" + 
			"<p style=\"line-height: 1.2; word-break: break-word; mso-line-height-alt: NaNpx; margin: 0;\"><strong><span style=\"font-size: 24px;\">${title}</span></strong></p>\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"<!--[if mso]></td></tr></table><![endif]-->\r\n" + 
			"<!--[if (!mso)&(!IE)]><!-->\r\n" + 
			"</div>\r\n" + 
			"<!--<![endif]-->\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n" + 
			"<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"<div style=\"background-color:transparent;\">\r\n" + 
			"<div class=\"block-grid\" style=\"Margin: 0 auto; min-width: 320px; max-width: 600px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: #ffffff;\">\r\n" + 
			"<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\r\n" + 
			"<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px\"><tr class=\"layout-full-width\" style=\"background-color:#ffffff\"><![endif]-->\r\n" + 
			"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\";width:600px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\r\n" + 
			"<div class=\"col num12\" style=\"min-width: 320px; max-width: 600px; display: table-cell; vertical-align: top; width: 600px;\">\r\n" + 
			"<div style=\"width:100% !important;\">\r\n" + 
			"<!--[if (!mso)&(!IE)]><!-->\r\n" + 
			"<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\r\n" + 
			"<!--<![endif]-->\r\n" + 
			"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\r\n" + 
			"<tbody>\r\n" + 
			"<tr style=\"vertical-align: top;\" valign=\"top\">\r\n" + 
			"<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 0px; padding-right: 0px; padding-bottom: 0px; padding-left: 0px;\" valign=\"top\">\r\n" + 
			"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"30\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 30px; width: 100%;\" valign=\"top\" width=\"100%\">\r\n" + 
			"<tbody>\r\n" + 
			"<tr style=\"vertical-align: top;\" valign=\"top\">\r\n" + 
			"<td height=\"30\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\r\n" + 
			"</tr>\r\n" + 
			"</tbody>\r\n" + 
			"</table>\r\n" + 
			"</td>\r\n" + 
			"</tr>\r\n" + 
			"</tbody>\r\n" + 
			"</table>\r\n" + 
			"<div align=\"left\" class=\"img-container left fixedwidth\" style=\"padding-right: 35px;padding-left: 35px;\">\r\n" + 
			"<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr style=\"line-height:0px\"><td style=\"padding-right: 35px;padding-left: 35px;\" align=\"left\"><![endif]--><img alt=\"บบบ\" border=\"0\" class=\"left fixedwidth\" src=\"https://blinqlabs.com/resources/email/images/decoration_points_2.png\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 120px; display: block;\" title=\"บบบ\" width=\"120\"/>\r\n" + 
			"<!--[if mso]></td></tr></table><![endif]-->\r\n" + 
			"</div>\r\n" + 
			"<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 35px; padding-left: 35px; padding-top: 10px; padding-bottom: 10px; font-family: Tahoma, sans-serif\"><![endif]-->\r\n" + 
			"<div style=\"color:#6325d0;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:35px;padding-bottom:10px;padding-left:35px;\">\r\n" + 
			"<div style=\"line-height: 1.2; font-size: 12px; color: #6325d0; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\r\n" + 
			"<p style=\"line-height: 1.2; word-break: break-word; mso-line-height-alt: NaNpx; margin: 0;\"><strong><span style=\"font-size: 18px;\">${message}</span></strong></p>\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"<!--[if mso]></td></tr></table><![endif]-->\r\n" + 
			"<!--[if (!mso)&(!IE)]><!-->\r\n" + 
			"</div>\r\n" + 
			"<!--<![endif]-->\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n" + 
			"<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"<div style=\"background-color:transparent;\">\r\n" + 
			"<div class=\"block-grid\" style=\"Margin: 0 auto; min-width: 320px; max-width: 600px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: #ffffff;\">\r\n" + 
			"<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\r\n" + 
			"<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px\"><tr class=\"layout-full-width\" style=\"background-color:#ffffff\"><![endif]-->\r\n" + 
			"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\";width:600px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\r\n" + 
			"<div class=\"col num12\" style=\"min-width: 320px; max-width: 600px; display: table-cell; vertical-align: top; width: 600px;\">\r\n" + 
			"<div style=\"width:100% !important;\">\r\n" + 
			"<!--[if (!mso)&(!IE)]><!-->\r\n" + 
			"<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\r\n" + 
			"<!--<![endif]-->\r\n" + 
			"<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px; font-family: Tahoma, sans-serif\"><![endif]-->\r\n" + 
			"<div style=\"color:#555555;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\r\n" + 
			"<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\r\n" + 
			"<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; text-align: center; mso-line-height-alt: 17px; margin: 0;\">${image}</p>\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"<!--[if mso]></td></tr></table><![endif]-->\r\n" + 
			"<!--[if (!mso)&(!IE)]><!-->\r\n" + 
			"</div>\r\n" + 
			"<!--<![endif]-->\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n" + 
			"<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"<div style=\"background-color:transparent;\">\r\n" + 
			"<div class=\"block-grid\" style=\"Margin: 0 auto; min-width: 320px; max-width: 600px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: #ffffff;\">\r\n" + 
			"<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\r\n" + 
			"<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px\"><tr class=\"layout-full-width\" style=\"background-color:#ffffff\"><![endif]-->\r\n" + 
			"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\";width:600px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\r\n" + 
			"<div class=\"col num12\" style=\"min-width: 320px; max-width: 600px; display: table-cell; vertical-align: top; width: 600px;\">\r\n" + 
			"<div style=\"width:100% !important;\">\r\n" + 
			"<!--[if (!mso)&(!IE)]><!-->\r\n" + 
			"<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\r\n" + 
			"<!--<![endif]-->\r\n" + 
			"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\r\n" + 
			"<tbody>\r\n" + 
			"<tr style=\"vertical-align: top;\" valign=\"top\">\r\n" + 
			"<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 0px; padding-right: 0px; padding-bottom: 0px; padding-left: 0px;\" valign=\"top\">\r\n" + 
			"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"30\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 30px; width: 100%;\" valign=\"top\" width=\"100%\">\r\n" + 
			"<tbody>\r\n" + 
			"<tr style=\"vertical-align: top;\" valign=\"top\">\r\n" + 
			"<td height=\"30\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\r\n" + 
			"</tr>\r\n" + 
			"</tbody>\r\n" + 
			"</table>\r\n" + 
			"</td>\r\n" + 
			"</tr>\r\n" + 
			"</tbody>\r\n" + 
			"</table>\r\n" + 
			"<div align=\"left\" class=\"img-container left fixedwidth\" style=\"padding-right: 35px;padding-left: 35px;\">\r\n" + 
			"<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr style=\"line-height:0px\"><td style=\"padding-right: 35px;padding-left: 35px;\" align=\"left\"><![endif]--><img alt=\"บบบ\" border=\"0\" class=\"left fixedwidth\" src=\"https://blinqlabs.com/resources/email/images/decoration_points_2.png\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 120px; display: block;\" title=\"บบบ\" width=\"120\"/>\r\n" + 
			"<!--[if mso]></td></tr></table><![endif]-->\r\n" + 
			"</div>\r\n" + 
			"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\r\n" + 
			"<tbody>\r\n" + 
			"<tr style=\"vertical-align: top;\" valign=\"top\">\r\n" + 
			"<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 0px; padding-right: 0px; padding-bottom: 0px; padding-left: 0px;\" valign=\"top\">\r\n" + 
			"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"30\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 30px; width: 100%;\" valign=\"top\" width=\"100%\">\r\n" + 
			"<tbody>\r\n" + 
			"<tr style=\"vertical-align: top;\" valign=\"top\">\r\n" + 
			"<td height=\"30\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\r\n" + 
			"</tr>\r\n" + 
			"</tbody>\r\n" + 
			"</table>\r\n" + 
			"</td>\r\n" + 
			"</tr>\r\n" + 
			"</tbody>\r\n" + 
			"</table>\r\n" + 
			"<!--[if (!mso)&(!IE)]><!-->\r\n" + 
			"</div>\r\n" + 
			"<!--<![endif]-->\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n" + 
			"<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"<div style=\"background-color:transparent;\">\r\n" + 
			"<div class=\"block-grid\" style=\"Margin: 0 auto; min-width: 320px; max-width: 600px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: #ffffff;\">\r\n" + 
			"<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;background-image:url('https://blinqlabs.com/resources/email/images/register_now_bg.png');background-position:center top;background-repeat:repeat\">\r\n" + 
			"<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px\"><tr class=\"layout-full-width\" style=\"background-color:#ffffff\"><![endif]-->\r\n" + 
			"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\";width:600px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:15px; padding-bottom:15px;\"><![endif]-->\r\n" + 
			"<div class=\"col num12\" style=\"min-width: 320px; max-width: 600px; display: table-cell; vertical-align: top; width: 600px;\">\r\n" + 
			"<div style=\"width:100% !important;\">\r\n" + 
			"<!--[if (!mso)&(!IE)]><!-->\r\n" + 
			"<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:15px; padding-bottom:15px; padding-right: 0px; padding-left: 0px;\">\r\n" + 
			"<!--<![endif]-->\r\n" + 
			"<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 5px;padding-left: 5px;\">\r\n" + 
			"<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr style=\"line-height:0px\"><td style=\"padding-right: 5px;padding-left: 5px;\" align=\"center\"><![endif]-->\r\n" + 
			"<div style=\"font-size:1px;line-height:10px\"> </div><a href=\"http://blinqlabs.com\" style=\"outline:none\" tabindex=\"-1\" target=\"_blank\"> <img align=\"center\" alt=\"Blinqlabs\" border=\"0\" class=\"center fixedwidth\" src=\"https://blinqlabs.com/resources/img/logos/white-logo-only.png\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 120px; display: block;\" title=\"Blinqlabs\" width=\"120\"/></a>\r\n" + 
			"<div style=\"font-size:1px;line-height:10px\"> </div>\r\n" + 
			"<!--[if mso]></td></tr></table><![endif]-->\r\n" + 
			"</div>\r\n" + 
			"<!--[if (!mso)&(!IE)]><!-->\r\n" + 
			"</div>\r\n" + 
			"<!--<![endif]-->\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n" + 
			"<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"<div style=\"background-color:transparent;\">\r\n" + 
			"<div class=\"block-grid\" style=\"Margin: 0 auto; min-width: 320px; max-width: 600px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: #ffffff;\">\r\n" + 
			"<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\r\n" + 
			"<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px\"><tr class=\"layout-full-width\" style=\"background-color:#ffffff\"><![endif]-->\r\n" + 
			"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\";width:600px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\r\n" + 
			"<div class=\"col num12\" style=\"min-width: 320px; max-width: 600px; display: table-cell; vertical-align: top; width: 600px;\">\r\n" + 
			"<div style=\"width:100% !important;\">\r\n" + 
			"<!--[if (!mso)&(!IE)]><!-->\r\n" + 
			"<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\r\n" + 
			"<!--<![endif]-->\r\n" + 
			"<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px; font-family: Tahoma, sans-serif\"><![endif]-->\r\n" + 
			"<div style=\"color:#555555;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\r\n" + 
			"<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\r\n" + 
			"<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; text-align: center; mso-line-height-alt: 17px; margin: 0;\">${dateTime}</p>\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"<!--[if mso]></td></tr></table><![endif]-->\r\n" + 
			"<!--[if (!mso)&(!IE)]><!-->\r\n" + 
			"</div>\r\n" + 
			"<!--<![endif]-->\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n" + 
			"<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"<div style=\"background-color:transparent;\">\r\n" + 
			"<div class=\"block-grid\" style=\"Margin: 0 auto; min-width: 320px; max-width: 600px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: transparent;\">\r\n" + 
			"<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\r\n" + 
			"<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px\"><tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\r\n" + 
			"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\";width:600px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\r\n" + 
			"<div class=\"col num12\" style=\"min-width: 320px; max-width: 600px; display: table-cell; vertical-align: top; width: 600px;\">\r\n" + 
			"<div style=\"width:100% !important;\">\r\n" + 
			"<!--[if (!mso)&(!IE)]><!-->\r\n" + 
			"<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\r\n" + 
			"<!--<![endif]-->\r\n" + 
			"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\r\n" + 
			"<tbody>\r\n" + 
			"<tr style=\"vertical-align: top;\" valign=\"top\">\r\n" + 
			"<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 0px; padding-right: 0px; padding-bottom: 0px; padding-left: 0px;\" valign=\"top\">\r\n" + 
			"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"20\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 20px; width: 100%;\" valign=\"top\" width=\"100%\">\r\n" + 
			"<tbody>\r\n" + 
			"<tr style=\"vertical-align: top;\" valign=\"top\">\r\n" + 
			"<td height=\"20\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\r\n" + 
			"</tr>\r\n" + 
			"</tbody>\r\n" + 
			"</table>\r\n" + 
			"</td>\r\n" + 
			"</tr>\r\n" + 
			"</tbody>\r\n" + 
			"</table>\r\n" + 
			"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" valign=\"top\" width=\"100%\">\r\n" + 
			"<tr style=\"vertical-align: top;\" valign=\"top\">\r\n" + 
			"<td align=\"center\" style=\"word-break: break-word; vertical-align: top; padding-top: 5px; padding-bottom: 5px; padding-left: 0px; padding-right: 0px; text-align: center; font-size: 0px;\" valign=\"top\">\r\n" + 
			"<div class=\"menu-links\">\r\n" + 
			"<!--[if mso]>\r\n" + 
			"<table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\r\n" + 
			"<tr>\r\n" + 
			"<td style=\"padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px\">\r\n" + 
			"<![endif]--><a href=\"https://blinqlabs.com/aboutus\" style=\"padding-top:10px;padding-bottom:10px;padding-left:10px;padding-right:10px;display:inline;color:#7b7b7b;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;font-size:12px;text-decoration:none;\">ABOUT US</a>\r\n" + 
			"<!--[if mso]></td><td style=\"padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px\"><![endif]--><a href=\"https://blinqlabs.com/privacyPolicy\" style=\"padding-top:10px;padding-bottom:10px;padding-left:10px;padding-right:10px;display:inline;color:#7b7b7b;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;font-size:12px;text-decoration:none;\">PRIVACY POLICY</a>\r\n" + 
			"<!--[if mso]></td><td style=\"padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px\"><![endif]-->\r\n" + 
			"</div>\r\n" + 
			"</td>\r\n" + 
			"</tr>\r\n" + 
			"</table>\r\n" + 
			"<table cellpadding=\"0\" cellspacing=\"0\" class=\"social_icons\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" valign=\"top\" width=\"100%\">\r\n" + 
			"<tbody>\r\n" + 
			"<tr style=\"vertical-align: top;\" valign=\"top\">\r\n" + 
			"<td style=\"word-break: break-word; vertical-align: top; padding-top: 10px; padding-right: 5px; padding-bottom: 10px; padding-left: 5px;\" valign=\"top\">\r\n" + 
			"<table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" class=\"social_table\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-tspace: 0; mso-table-rspace: 0; mso-table-bspace: 0; mso-table-lspace: 0;\" valign=\"top\">\r\n" + 
			"<tbody>\r\n" + 
			"<tr align=\"center\" style=\"vertical-align: top; display: inline-block; text-align: center;\" valign=\"top\">\r\n" + 
			"<td style=\"word-break: break-word; vertical-align: top; padding-bottom: 0; padding-right: 5px; padding-left: 5px;\" valign=\"top\"><a href=\"https://www.linkedin.com/company/55057942\" target=\"_blank\"><img alt=\"LinkedIn\" height=\"32\" src=\"https://blinqlabs.com/resources/email/images/linkedin2x.png\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; display: block;\" title=\"LinkedIn\" width=\"32\"/></a></td>\r\n" + 
			"<td style=\"word-break: break-word; vertical-align: top; padding-bottom: 0; padding-right: 5px; padding-left: 5px;\" valign=\"top\"><a href=\"https://twitter.com/blinqlabs\" target=\"_blank\"><img alt=\"Twitter\" height=\"32\" src=\"https://blinqlabs.com/resources/email/images/twitter2x.png\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; display: block;\" title=\"Twitter\" width=\"32\"/></a></td>\r\n" + 
			"</tr>\r\n" + 
			"</tbody>\r\n" + 
			"</table>\r\n" + 
			"</td>\r\n" + 
			"</tr>\r\n" + 
			"</tbody>\r\n" + 
			"</table>\r\n" + 
			"<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px; font-family: Tahoma, sans-serif\"><![endif]-->\r\n" + 
			"<div style=\"color:#7b7b7b;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\r\n" + 
			"<div style=\"line-height: 1.2; font-size: 12px; color: #7b7b7b; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\r\n" + 
			"<p style=\"font-size: 12px; line-height: 1.2; word-break: break-word; text-align: center; mso-line-height-alt: 14px; margin: 0;\"><span style=\"font-size: 12px;\">ฉ 2020 Blinqlabs. All Rights Reserved.</span></p>\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"<!--[if mso]></td></tr></table><![endif]-->\r\n" + 
			"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\r\n" + 
			"<tbody>\r\n" + 
			"<tr style=\"vertical-align: top;\" valign=\"top\">\r\n" + 
			"<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 0px; padding-right: 0px; padding-bottom: 0px; padding-left: 0px;\" valign=\"top\">\r\n" + 
			"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"20\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 20px; width: 100%;\" valign=\"top\" width=\"100%\">\r\n" + 
			"<tbody>\r\n" + 
			"<tr style=\"vertical-align: top;\" valign=\"top\">\r\n" + 
			"<td height=\"20\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\r\n" + 
			"</tr>\r\n" + 
			"</tbody>\r\n" + 
			"</table>\r\n" + 
			"</td>\r\n" + 
			"</tr>\r\n" + 
			"</tbody>\r\n" + 
			"</table>\r\n" + 
			"<!--[if (!mso)&(!IE)]><!-->\r\n" + 
			"</div>\r\n" + 
			"<!--<![endif]-->\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n" + 
			"<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"<div style=\"background-color:transparent;\">\r\n" + 
			"<div class=\"block-grid\" style=\"Margin: 0 auto; min-width: 320px; max-width: 600px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: transparent;\">\r\n" + 
			"<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\r\n" + 
			"<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px\"><tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\r\n" + 
			"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\";width:600px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\r\n" + 
			"<div class=\"col num12\" style=\"min-width: 320px; max-width: 600px; display: table-cell; vertical-align: top; width: 600px;\">\r\n" + 
			"<div style=\"width:100% !important;\">\r\n" + 
			"<!--[if (!mso)&(!IE)]><!-->\r\n" + 
			"<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\r\n" + 
			"<!--<![endif]-->\r\n" + 
			"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\r\n" + 
			"<tbody>\r\n" + 
			"<tr style=\"vertical-align: top;\" valign=\"top\">\r\n" + 
			"<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\r\n" + 
			"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 1px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\r\n" + 
			"<tbody>\r\n" + 
			"<tr style=\"vertical-align: top;\" valign=\"top\">\r\n" + 
			"<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\r\n" + 
			"</tr>\r\n" + 
			"</tbody>\r\n" + 
			"</table>\r\n" + 
			"</td>\r\n" + 
			"</tr>\r\n" + 
			"</tbody>\r\n" + 
			"</table>\r\n" + 
			"<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px; font-family: Arial, sans-serif\"><![endif]-->\r\n" + 
			"<div style=\"color:#555555;font-family:'Open Sans', 'Helvetica Neue', Helvetica, Arial, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\r\n" + 
			"<div style=\"line-height: 1.2; font-size: 12px; font-family: 'Open Sans', 'Helvetica Neue', Helvetica, Arial, sans-serif; color: #555555; mso-line-height-alt: 14px;\">\r\n" + 
			"<p style=\"font-size: 10px; line-height: 1.2; text-align: center; font-family: 'Open Sans', 'Helvetica Neue', Helvetica, Arial, sans-serif; word-break: break-word; mso-line-height-alt: 12px; margin: 0;\"><span style=\"font-size: 10px;\">${disclaimer}</span></p>\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"<!--[if mso]></td></tr></table><![endif]-->\r\n" + 
			"<!--[if (!mso)&(!IE)]><!-->\r\n" + 
			"</div>\r\n" + 
			"<!--<![endif]-->\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n" + 
			"<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"</div>\r\n" + 
			"<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n" + 
			"</td>\r\n" + 
			"</tr>\r\n" + 
			"</tbody>\r\n" + 
			"</table>\r\n" + 
			"<!--[if (IE)]></div><![endif]-->\r\n" + 
			"</body>\r\n" + 
			"</html>";
	
	private String disclaimer = "This e-mail may contain confidential, privileged, and non-public information. It is intended only for the recipient(s). If you are not an intended recipient of this e-mail, please notify the sender. Unauthorized use, dissemination, distribution, or reproduction of this message is prohibited and may be unlawful.";

	public String getEmailTemplate() {
		return emailTemplate;
	}

	public void setEmailTemplate(String emailTemplate) {
		this.emailTemplate = emailTemplate;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	
	
}
