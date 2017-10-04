package es.unizar.webeng.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * Component that processes client requests.
 * <p>
 * The annotation @Controller is used so that the Spring container can detect, create and configure this component
 * when automatic configuration and wiring is used.
 */
@Controller
public class HelloController {
    /**
     * The annotation @Value is used to inject the value of the key "app.message" into the variable.
     * If the key is not defined, the default value "Hello World" will be injected instead.
     * Message variable is a message to display.
     */
    @Value("${app.message:Hello World}")
    private String messageMorning;
    @Value("${app.message:Hello World}")
    private String messageAfternoon;
    @Value("${app.message:Hello World}")
    private String messageEvening;
    @Value("${app.message:Hello World}")
    private String messageNight;	

    /**
     * Returns the logical name of the view that will be rendered when a GET request for "/" comes in.
     * <p>
     * It populates the model with some welcome information
     * (the current date and time and a simple welcome message based on the hour of the day)
     * that the view is going to receive.
     * <p>
     * The annotation @GetMapping is used to specify that the method is going to get called only when a GET request
     * for "/" comes in.
     *
     * @param model  the information that is going to be handed off to the view
     * @return the logical name of the view
     */
    @GetMapping("/")
    public String welcome(Map<String, Object> model) {
	Date date = new Date();
	//Assign in the key "time" a new date.
        model.put("time", date);

	//Creates a new calendar instance
	Calendar calendar = GregorianCalendar.getInstance();
	//Assigns calendar to given date 
	calendar.setTime(date);   
	//Gets hour in 24h format
	int hour = calendar.get(Calendar.HOUR_OF_DAY); 

	//Assign in the key "message" the value of the specific message variable.
	if (hour>=8 && hour<12){
		model.put("message", messageMorning);
	} else if (hour>=12 && hour<18){
		model.put("message", messageAfternoon);
	} else if (hour>=18){
		model.put("message", messageEvening);
	} else {
		model.put("message", messageNight);
	}
	
        return "welcome";
    }
}
