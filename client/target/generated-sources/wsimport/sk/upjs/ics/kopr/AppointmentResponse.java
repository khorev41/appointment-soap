
package sk.upjs.ics.kopr;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Appointment" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="doctorID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="doctorName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "appointment"
})
@XmlRootElement(name = "AppointmentResponse")
public class AppointmentResponse {

    @XmlElement(name = "Appointment", required = true)
    protected List<AppointmentResponse.Appointment> appointment;

    /**
     * Gets the value of the appointment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the appointment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAppointment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AppointmentResponse.Appointment }
     * 
     * 
     */
    public List<AppointmentResponse.Appointment> getAppointment() {
        if (appointment == null) {
            appointment = new ArrayList<AppointmentResponse.Appointment>();
        }
        return this.appointment;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="doctorID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="doctorName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "doctorID",
        "doctorName",
        "date",
        "time",
        "duration"
    })
    public static class Appointment {

        protected int doctorID;
        @XmlElement(required = true)
        protected String doctorName;
        @XmlElement(required = true)
        protected String date;
        @XmlElement(required = true)
        protected String time;
        protected int duration;

        /**
         * Gets the value of the doctorID property.
         * 
         */
        public int getDoctorID() {
            return doctorID;
        }

        /**
         * Sets the value of the doctorID property.
         * 
         */
        public void setDoctorID(int value) {
            this.doctorID = value;
        }

        /**
         * Gets the value of the doctorName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDoctorName() {
            return doctorName;
        }

        /**
         * Sets the value of the doctorName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDoctorName(String value) {
            this.doctorName = value;
        }

        /**
         * Gets the value of the date property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDate() {
            return date;
        }

        /**
         * Sets the value of the date property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDate(String value) {
            this.date = value;
        }

        /**
         * Gets the value of the time property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTime() {
            return time;
        }

        /**
         * Sets the value of the time property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTime(String value) {
            this.time = value;
        }

        /**
         * Gets the value of the duration property.
         * 
         */
        public int getDuration() {
            return duration;
        }

        /**
         * Sets the value of the duration property.
         * 
         */
        public void setDuration(int value) {
            this.duration = value;
        }

    }

}
