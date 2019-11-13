package urbtec.silentmailer;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SendMail extends CordovaPlugin {

	public static final String ACTION_SEND = "send";
	public static String posind = "";

	public boolean execute(
		String action,
		JSONArray jsonArgs,
		final CallbackContext callbackContext) throws JSONException {

		if (ACTION_SEND.equals(action)) {
			// Get the json arguments as final for thread usage.
			final JSONObject args = jsonArgs.getJSONObject(0);

			// Run in a thread to not block the webcore thread.
			cordova.getThreadPool().execute(new Runnable() {
				// Thread method.
				public void run() {
					// Try to send the the mail.
					try {
						// Get the arguments.
						String subject = args.getString("subject");
						String body = args.getString("body");
						String sender = args.getString("sender");
						String password = args.getString("password");
						String recipients = args.getString("recipients");
						String attachment = null;
						if (args.has("attachment")) {
							attachment = args.getString("attachment");
						}
						
						String mailhost = "smtp.gmail.com";
						if (args.has("mailhost")) {
							mailhost = args.getString("mailhost");
						}
						
						String port = "465";
						if (args.has("port")) {
							port = args.getString("port");
						}
						
						posind = "A";
						// Create the sender
						//MailSender mailSender = new MailSender(sender, password, mailhost, port);
						posind = "B";
						// Send the mail.
						//mailSender.sendMail(subject, body, sender, recipients, attachment);
						posind = "C";
						// Thread safe callback.
						callbackContext.success();
					} catch (Exception e) {
						// Catch error.
						callbackContext.error(e.getMessage()+" "+posind);
						callbackContext.error(e.toString()+" "+posind);
						
					}
				}
			});

			return true;
		}

		return false;
	}
}
