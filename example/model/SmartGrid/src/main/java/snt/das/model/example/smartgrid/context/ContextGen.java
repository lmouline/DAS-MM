package snt.das.model.example.smartgrid.context;

import ldas.model.*;
import greycat.Task;
import greycat.Tasks;

@Deprecated
public class ContextGen {
    private static final String CONTEXT = "context";

    private static final String ENTITY = "entity";
    private static final String CONCENTRATOR = "concentrator";
    private static final String CENTRAL_SYSTEM = "centralSystem";
    private static final String METER = "meter";
    private static final String WATER_METER = "waterMeter";
    private static final String GAS_METER = "gasMeter";
    private static final String SMART_METER = "smartMeter";
    private static final String CONSUMPTION = "consumption";
    private static final String PLC = "PLC";
    private static final String LOCATION = "location";
    private static final String WIRED_COMMUNICATION_MEDIA = "wiredCommunicationMedia";
    private static final String COMMUNICATION_MEDIA = "CommunicationMedia";
    private static final String WIRELESS_COMMUNICATION_MEDIA = "WirelesCommunicationMedia";
    private static final String CUSTOMER = "customer";

    private static final String CURRENT_PROP = "property";
    private static final String CURRENT_SRC = "source";
    private static final String CURRENT_UC = "source";

    private static final Task STRUCT_GEN = Tasks.newTask()
            .createTypedNode(Structure.META.name)
            .setAttribute(Structure.NAME.name, Structure.NAME.type, "Entity")
            .defineAsGlobalVar(ENTITY)

            .createTypedNode(Structure.META.name)
            .setAttribute(Structure.NAME.name, Structure.NAME.type, "Concentrator")
            .addVarTo(Structure.PARENT.name, ENTITY)
            .defineAsGlobalVar(CONCENTRATOR)

            .createTypedNode(Structure.META.name)
            .setAttribute(Structure.NAME.name, Structure.NAME.type, "CentralSystem")
            .addVarTo(Structure.PARENT.name, ENTITY)
            .defineAsGlobalVar(CENTRAL_SYSTEM)

            .createTypedNode(Structure.META.name)
            .setAttribute(Structure.NAME.name, Structure.NAME.type, "Meter")
            .addVarTo(Structure.PARENT.name, ENTITY)
            .defineAsGlobalVar(METER)

            .createTypedNode(Structure.META.name)
            .setAttribute(Structure.NAME.name, Structure.NAME.type, "WaterMeter")
            .addVarTo(Structure.PARENT.name, METER)
            .defineAsGlobalVar(WATER_METER)

            .createTypedNode(Structure.META.name)
            .setAttribute(Structure.NAME.name, Structure.NAME.type, "GasMeter")
            .addVarTo(Structure.PARENT.name, METER)
            .defineAsGlobalVar(GAS_METER)

            .createTypedNode(Structure.META.name)
            .setAttribute(Structure.NAME.name, Structure.NAME.type, "SmartMeter")
            .addVarTo(Structure.PARENT.name, METER)
            .defineAsGlobalVar(SMART_METER)

            .createTypedNode(Structure.META.name)
            .setAttribute(Structure.NAME.name, Structure.NAME.type, "Consumption")
            .defineAsGlobalVar(CONSUMPTION)

            .createTypedNode(Structure.META.name)
            .setAttribute(Structure.NAME.name, Structure.NAME.type, "Location")
            .defineAsGlobalVar(LOCATION)

            .createTypedNode(Structure.META.name)
            .setAttribute(Structure.NAME.name, Structure.NAME.type, "CommunicationMedia")
            .defineAsGlobalVar(COMMUNICATION_MEDIA)

            .createTypedNode(Structure.META.name)
            .setAttribute(Structure.NAME.name, Structure.NAME.type, "WirelessCommunicationMedia")
            .defineAsGlobalVar(WIRELESS_COMMUNICATION_MEDIA)
            .addVarTo(Structure.PARENT.name, COMMUNICATION_MEDIA)

            .createTypedNode(Structure.META.name)
            .setAttribute(Structure.NAME.name, Structure.NAME.type, "WiredCommunicationMedia")
            .defineAsGlobalVar(WIRED_COMMUNICATION_MEDIA)
            .addVarTo(Structure.PARENT.name, COMMUNICATION_MEDIA)

            .createTypedNode(Structure.META.name)
            .setAttribute(Structure.NAME.name, Structure.NAME.type, "PLC")
            .defineAsGlobalVar(PLC)
            .addVarTo(Structure.PARENT.name, WIRED_COMMUNICATION_MEDIA)

            .createTypedNode(Structure.META.name)
            .setAttribute(Structure.NAME.name, Structure.NAME.type, "Customer")
            .defineAsGlobalVar(CUSTOMER)

            .readVar(CONTEXT)
            .addVarTo(Context.STRUCTURES.name, ENTITY)
            .addVarTo(Context.STRUCTURES.name, CONCENTRATOR)
            .addVarTo(Context.STRUCTURES.name, CENTRAL_SYSTEM)
            .addVarTo(Context.STRUCTURES.name, METER)
            .addVarTo(Context.STRUCTURES.name, WATER_METER)
            .addVarTo(Context.STRUCTURES.name, GAS_METER)
            .addVarTo(Context.STRUCTURES.name, SMART_METER)
            .addVarTo(Context.STRUCTURES.name, CONSUMPTION)
            .addVarTo(Context.STRUCTURES.name, LOCATION)
            .addVarTo(Context.STRUCTURES.name, COMMUNICATION_MEDIA)
            .addVarTo(Context.STRUCTURES.name, WIRELESS_COMMUNICATION_MEDIA)
            .addVarTo(Context.STRUCTURES.name, WIRED_COMMUNICATION_MEDIA)
            .addVarTo(Context.STRUCTURES.name, PLC)
            .addVarTo(Context.STRUCTURES.name, CUSTOMER);

    private static final Task CONSUMPTION_PROP_GEN = Tasks.newTask()
            .createTypedNode(Attribute.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "measuringTime")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "true")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, "Date")
            .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
            .setAsVar(CURRENT_PROP)
            .readVar(CONSUMPTION)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)

            .createTypedNode(Attribute.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "consumption")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "true")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, Constants.TYPE_NUMERICAL)
            .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
            .setAsVar(CURRENT_PROP)
            .readVar(CONSUMPTION)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)

            .createTypedNode(Attribute.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "production")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "true")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, Constants.TYPE_NUMERICAL)
            .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
            .setAsVar(CURRENT_PROP)
            .readVar(CONSUMPTION)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)

            .createTypedNode(Attribute.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "voltage")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "true")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, Constants.TYPE_NUMERICAL)
            .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
            .setAsVar(CURRENT_PROP)
            .readVar(CONSUMPTION)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)

            .createTypedNode(Attribute.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "current")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "true")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, Constants.TYPE_NUMERICAL)
            .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
            .setAsVar(CURRENT_PROP)
            .readVar(CONSUMPTION)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP);

    private static final Task ENTITY_PROP_GEN = Tasks.newTask()
            .createTypedNode(Relation.META.name)
            .setAttribute(Relation.NAME.name, Relation.NAME.type, "consumptionData")
            .setAttribute(Relation.ISTEMPORAL.name, Relation.ISTEMPORAL.type, "true")
            .setAttribute(Relation.ISSTATIC.name, Relation.ISSTATIC.type, "false")
            .addVarTo(Relation.TYPEREL.name, CONSUMPTION)
            .setAsVar(CURRENT_PROP)
            .readVar(ENTITY)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)

            .createTypedNode(Relation.META.name)
            .setAttribute(Relation.NAME.name, Relation.NAME.type, "registeredEntities")
            .setAttribute(Relation.ISTEMPORAL.name, Relation.ISTEMPORAL.type, "true")
            .setAttribute(Relation.ISSTATIC.name, Relation.ISSTATIC.type, "false")
            .addVarTo(Relation.TYPEREL.name, ENTITY)
            .setAsVar(CURRENT_PROP)
            .readVar(ENTITY)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)
            // create source
            .createTypedNode(Sensor.META.name)
//            .addVarTo(Sensor.INFORMATION.name, ENTITY)
            .setAsVar(CURRENT_SRC)
            .readVar(CURRENT_PROP)
            .addVarTo(Relation.SOURCE.name, CURRENT_SRC)
            // create uncertainty
            .createTypedNode(Uncertainty.META.name)
            .setAttribute(Uncertainty.VALUE.name, Uncertainty.VALUE.type, "0.9")
            .setAsVar(CURRENT_UC)
            .readVar(CURRENT_PROP)
            .addVarTo(Relation.UNCERTAINTY.name, CURRENT_UC)

            .createTypedNode(Relation.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "registerBy")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "true")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .addVarTo(Relation.TYPEREL.name, ENTITY)
            .setAsVar(CURRENT_PROP + "1")
            .addVarTo(Relation.OPPOSITE.name, CURRENT_PROP)
            .readVar(CURRENT_PROP)
            .addVarTo(Relation.OPPOSITE.name, CURRENT_PROP + "1")
            .readVar(ENTITY)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP + "1")

            .createTypedNode(Attribute.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "communicationActive")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "true")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, Constants.TYPE_BOOLEAN)
            .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
            .setAsVar(CURRENT_PROP)
            .readVar(ENTITY)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)

            .createTypedNode(Attribute.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "serialNumber")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "false")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "true")
            .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, Constants.TYPE_TEXT)
            .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
            .setAsVar(CURRENT_PROP)
            .readVar(ENTITY)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)

            .createTypedNode(Attribute.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "id")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "true")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, Constants.TYPE_TEXT)
            .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
            .setAsVar(CURRENT_PROP)
            .readVar(ENTITY)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)

            .createTypedNode(Relation.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "consumptionData")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "true")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .addVarTo(Relation.TYPEREL.name, COMMUNICATION_MEDIA)
            .setAsVar(CURRENT_PROP)
            .readVar(ENTITY)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)

            .createTypedNode(Relation.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "location")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "true")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .addVarTo(Relation.TYPEREL.name, LOCATION)
            .setAsVar(CURRENT_PROP)
            .readVar(ENTITY)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)
            ;

    private static final Task METER_PROP_GEN = Tasks.newTask()
            .createTypedNode(Relation.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "customer")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "true")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .addVarTo(Relation.TYPEREL.name, CUSTOMER)
            .setAsVar(CURRENT_PROP)
            .readVar(METER)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP);

    private static final Task SMARTMETER_PROP_GEN = Tasks.newTask()
            .createTypedNode(Attribute.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "isRepeater")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "true")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, Constants.TYPE_BOOLEAN)
            .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
            .setAsVar(CURRENT_PROP)
            .readVar(SMART_METER)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)

            .createTypedNode(Attribute.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "distance2concentrator")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "true")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, Constants.TYPE_NUMERICAL)
            .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
            .setAsVar(CURRENT_PROP)
            .readVar(SMART_METER)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)

            .createTypedNode(Attribute.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "highPowerCurrentActive")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "true")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, Constants.TYPE_BOOLEAN)
            .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
            .setAsVar(CURRENT_PROP)
            .readVar(SMART_METER)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)

            .createTypedNode(Attribute.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "electricityActive")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "true")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, Constants.TYPE_BOOLEAN)
            .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
            .setAsVar(CURRENT_PROP)
            .readVar(SMART_METER)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)

            .createTypedNode(Attribute.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "hops2concentrator")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "true")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, Constants.TYPE_NUMERICAL)
            .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
            .setAsVar(CURRENT_PROP)
            .readVar(SMART_METER)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)

            .createTypedNode(Attribute.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "duration2Read")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "true")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, Constants.TYPE_NUMERICAL)
            .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
            .setAsVar(CURRENT_PROP)
            .readVar(SMART_METER)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)

            .createTypedNode(Attribute.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "maxAllowedPower")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "true")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, Constants.TYPE_NUMERICAL)
            .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
            .setAsVar(CURRENT_PROP)
            .readVar(SMART_METER)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP);

    private static final Task LOCATION_PROP_GEN = Tasks.newTask()
            .createTypedNode(Attribute.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "address")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "false")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, Constants.TYPE_TEXT)
            .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
            .setAsVar(CURRENT_PROP)
            .readVar(LOCATION)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)

            .createTypedNode(Attribute.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "gpsLatitude")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "true")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, Constants.TYPE_TEXT)
            .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
            .setAsVar(CURRENT_PROP)
            .readVar(LOCATION)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)

            .createTypedNode(Attribute.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "gpsLongitude")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "true")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, Constants.TYPE_TEXT)
            .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
            .setAsVar(CURRENT_PROP)
            .readVar(LOCATION)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP);

    private static final Task COMM_PROP_GEN = Tasks.newTask()
            .createTypedNode(Attribute.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "id")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "true")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, Constants.TYPE_TEXT)
            .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
            .setAsVar(CURRENT_PROP)
            .readVar(COMMUNICATION_MEDIA)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)

            .createTypedNode(Attribute.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "payload")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "true")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, Constants.TYPE_NUMERICAL)
            .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
            .setAsVar(CURRENT_PROP)
            .readVar(COMMUNICATION_MEDIA)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP);

    private static final Task WIRED_COMM_PROP_GEN = Tasks.newTask()
            .createTypedNode(Attribute.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "material")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "false")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, Constants.TYPE_TEXT)
            .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
            .setAsVar(CURRENT_PROP)
            .readVar(WIRED_COMMUNICATION_MEDIA)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)

            .createTypedNode(Attribute.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "size")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "false")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "true")
            .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, Constants.TYPE_NUMERICAL)
            .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
            .setAsVar(CURRENT_PROP)
            .readVar(WIRED_COMMUNICATION_MEDIA)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)

            .createTypedNode(Attribute.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "remark")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "true")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, Constants.TYPE_TEXT)
            .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
            .setAsVar(CURRENT_PROP)
            .readVar(WIRED_COMMUNICATION_MEDIA)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)

            .createTypedNode(Relation.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "endPoint")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "false")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .addVarTo(Relation.TYPEREL.name, LOCATION)
            .setAsVar(CURRENT_PROP)
            .readVar(WIRED_COMMUNICATION_MEDIA)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP)

            .createTypedNode(Relation.META.name)
            .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "startPoint")
            .setAttribute(Attribute.ISTEMPORAL.name, Attribute.ISTEMPORAL.type, "false")
            .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
            .addVarTo(Relation.TYPEREL.name, LOCATION)
            .setAsVar(CURRENT_PROP)
            .readVar(WIRED_COMMUNICATION_MEDIA)
            .addVarTo(Structure.PROPERTIES.name, CURRENT_PROP);


    public static final Task CONTEXT_GEN_OLD = Tasks.newTask()
            .createTypedNode(Context.META.name)
            .setAttribute(Context.NAME.name, Context.NAME.type, "smartGridCtx")
            .setAsVar(CONTEXT)
            .updateIndex(Contexts.META.name)
            .pipe(STRUCT_GEN, CONSUMPTION_PROP_GEN, ENTITY_PROP_GEN, METER_PROP_GEN, SMARTMETER_PROP_GEN, LOCATION_PROP_GEN, COMM_PROP_GEN, WIRED_COMM_PROP_GEN)
            ;

}
