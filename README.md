# A small journey in an eXtremme Programming team

This is the Backend challenge for Creditas Valencia.

You are part of an [Extreme Programming](http://www.extremeprogramming.org/) team. You all share [some values](http://www.extremeprogramming.org/values.html) and have agreed to the same [rules](http://www.extremeprogramming.org/rules.html).

You already spent a whole week discovering what the existing payment stream is like. After your investigation it turns out the sytem is fragile because it requires large modifications whenever a new item is sent or removed. The code was written by a previous team and you suspect they didn't follow good practices.

After some product discovery done together with the Product Manager and some other members of the team you decide together that the payment system needs to evolve and it should be able to handle the following scenarios:

+ If the payment is for a physical item, you must generate one `shipping label` for it to be placed in the shipping box.
+ If payment is for a subscription service, you must activate the subscription, and notify the user via email about this.
+ If the payment is for an ordinary book, you must generate a `shipping label` with a notification that it is a tax-exempt item.
+ If the payment is for any digital media (music, video), in addition to sending the description of the purchase by email to the buyer, grant a discount voucher of 10% to the buyer associated with the payment.

## Tips

* Feel free to modify/refactor the bootstrap files if you think it's necessary.
* Don’t over-engineer your solution! Your Extremme Programming team loves clean and cohesive code, so try to keep it simple.
* It’s ok if you use third-party libraries, but be aware that your team prefers a vanilla solution.
* The challenge **does not require a final working code necessarily**. We want to understand how you solve problems. If you have any questions or doubts, feel free to ask.
* Describe your modifications and reasoning in a separate markdown file.
* What language? Kotlin, Ruby, Python, Java or PHP.


## Out of scope

* **It is not necessary** to create the implementations for sending emails, to print the shipping label, etc. For these cases (email, shipping label) **only create method calls**, to indicate that it would be the place where the sending would take place.
* **It is not necessary** to use a databae. Although in real life you would, for the challenge, in-memory data is enough.
* **It is not necessary** to dockerise your application.
