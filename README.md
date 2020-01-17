# backend-challenge-vlc
Backend challenge for Creditas Valencia

You have been assigned to prototype a new version of an existing payment stream which is fragile and not scalable, since it requires large modifications whenever a new item is sent or removed.

The new prototype should be able to handle the following scenarios:

+ If the payment is for a physical item, you must generate one `shipping label` for it to be placed in the shipping box;
+ If payment is a service subscription, you must activate the subscription, and notify the user via email about this;
+ If the payment is an ordinary book, you must generate a `shipping label` with a notification that it is a tax-exempt item.
+ If payment of any digital media (music, video), in addition to sending the description of the purchase by email to the buyer, grant a discount voucher of 10% to the buyer associated with the payment.

**It is not necessary** to create the implementations for sending emails, to print the shipping label , etc. For these cases (email, shipping label) **only create method calls**, to indicate that it would be the place where the sending would take place.

Feel free to modify / refactor the bootstrap file if you feel it is necessary.

Since the proposal does not require a final working code, the use of libraries is allowed to facilitate the implementation.

Please describe your modifications and reasoning in a separate text file or markdown.

What language? Kotlin, Ruby, Python, Java or PHP

