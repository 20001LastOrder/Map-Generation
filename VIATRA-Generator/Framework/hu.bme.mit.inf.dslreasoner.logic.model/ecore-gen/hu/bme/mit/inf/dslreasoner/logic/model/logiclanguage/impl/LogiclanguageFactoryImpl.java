/**
 */
package hu.bme.mit.inf.dslreasoner.logic.model.logiclanguage.impl;

import hu.bme.mit.inf.dslreasoner.logic.model.logiclanguage.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LogiclanguageFactoryImpl extends EFactoryImpl implements LogiclanguageFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LogiclanguageFactory init() {
		try {
			LogiclanguageFactory theLogiclanguageFactory = (LogiclanguageFactory)EPackage.Registry.INSTANCE.getEFactory(LogiclanguagePackage.eNS_URI);
			if (theLogiclanguageFactory != null) {
				return theLogiclanguageFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new LogiclanguageFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogiclanguageFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case LogiclanguagePackage.DEFINED_ELEMENT: return createDefinedElement();
			case LogiclanguagePackage.TYPE_DEFINITION: return createTypeDefinition();
			case LogiclanguagePackage.COMPLEX_TYPE_REFERENCE: return createComplexTypeReference();
			case LogiclanguagePackage.INT_TYPE_REFERENCE: return createIntTypeReference();
			case LogiclanguagePackage.BOOL_TYPE_REFERENCE: return createBoolTypeReference();
			case LogiclanguagePackage.REAL_TYPE_REFERENCE: return createRealTypeReference();
			case LogiclanguagePackage.SYMBOLIC_VALUE: return createSymbolicValue();
			case LogiclanguagePackage.INT_LITERAL: return createIntLiteral();
			case LogiclanguagePackage.BOOL_LITERAL: return createBoolLiteral();
			case LogiclanguagePackage.REAL_LITERAL: return createRealLiteral();
			case LogiclanguagePackage.VARIABLE: return createVariable();
			case LogiclanguagePackage.EXISTS: return createExists();
			case LogiclanguagePackage.FORALL: return createForall();
			case LogiclanguagePackage.AND: return createAnd();
			case LogiclanguagePackage.OR: return createOr();
			case LogiclanguagePackage.IMPL: return createImpl();
			case LogiclanguagePackage.NOT: return createNot();
			case LogiclanguagePackage.IFF: return createIff();
			case LogiclanguagePackage.EQUALS: return createEquals();
			case LogiclanguagePackage.DISTINCT: return createDistinct();
			case LogiclanguagePackage.LESS_THAN: return createLessThan();
			case LogiclanguagePackage.MORE_THAN: return createMoreThan();
			case LogiclanguagePackage.LESS_OR_EQUAL_THAN: return createLessOrEqualThan();
			case LogiclanguagePackage.MORE_OR_EQUAL_THAN: return createMoreOrEqualThan();
			case LogiclanguagePackage.PLUS: return createPlus();
			case LogiclanguagePackage.MINUS: return createMinus();
			case LogiclanguagePackage.MULTIPLY: return createMultiply();
			case LogiclanguagePackage.DIVISON: return createDivison();
			case LogiclanguagePackage.MOD: return createMod();
			case LogiclanguagePackage.ASSERTION: return createAssertion();
			case LogiclanguagePackage.CONSTANT_DEFINITION: return createConstantDefinition();
			case LogiclanguagePackage.RELATION_DEFINITION: return createRelationDefinition();
			case LogiclanguagePackage.FUNCTION_DEFINITION: return createFunctionDefinition();
			case LogiclanguagePackage.IF_THEN_ELSE: return createIfThenElse();
			case LogiclanguagePackage.CONSTANT_DECLARATION: return createConstantDeclaration();
			case LogiclanguagePackage.RELATION_DECLARATION: return createRelationDeclaration();
			case LogiclanguagePackage.FUNCTION_DECLARATION: return createFunctionDeclaration();
			case LogiclanguagePackage.TYPE_DECLARATION: return createTypeDeclaration();
			case LogiclanguagePackage.UNKNOWN_BECAUSE_UNINTERPRETED: return createUnknownBecauseUninterpreted();
			case LogiclanguagePackage.INSTANCE_OF: return createInstanceOf();
			case LogiclanguagePackage.STRING_TYPE_REFERENCE: return createStringTypeReference();
			case LogiclanguagePackage.STRING_LITERAL: return createStringLiteral();
			case LogiclanguagePackage.TRANSITIVE_CLOSURE: return createTransitiveClosure();
			case LogiclanguagePackage.POW: return createPow();
			case LogiclanguagePackage.SUM: return createSum();
			case LogiclanguagePackage.COUNT: return createCount();
			case LogiclanguagePackage.MIN: return createMin();
			case LogiclanguagePackage.MAX: return createMax();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DefinedElement createDefinedElement() {
		DefinedElementImpl definedElement = new DefinedElementImpl();
		return definedElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TypeDefinition createTypeDefinition() {
		TypeDefinitionImpl typeDefinition = new TypeDefinitionImpl();
		return typeDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ComplexTypeReference createComplexTypeReference() {
		ComplexTypeReferenceImpl complexTypeReference = new ComplexTypeReferenceImpl();
		return complexTypeReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IntTypeReference createIntTypeReference() {
		IntTypeReferenceImpl intTypeReference = new IntTypeReferenceImpl();
		return intTypeReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BoolTypeReference createBoolTypeReference() {
		BoolTypeReferenceImpl boolTypeReference = new BoolTypeReferenceImpl();
		return boolTypeReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RealTypeReference createRealTypeReference() {
		RealTypeReferenceImpl realTypeReference = new RealTypeReferenceImpl();
		return realTypeReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SymbolicValue createSymbolicValue() {
		SymbolicValueImpl symbolicValue = new SymbolicValueImpl();
		return symbolicValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IntLiteral createIntLiteral() {
		IntLiteralImpl intLiteral = new IntLiteralImpl();
		return intLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BoolLiteral createBoolLiteral() {
		BoolLiteralImpl boolLiteral = new BoolLiteralImpl();
		return boolLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RealLiteral createRealLiteral() {
		RealLiteralImpl realLiteral = new RealLiteralImpl();
		return realLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Variable createVariable() {
		VariableImpl variable = new VariableImpl();
		return variable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Exists createExists() {
		ExistsImpl exists = new ExistsImpl();
		return exists;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Forall createForall() {
		ForallImpl forall = new ForallImpl();
		return forall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public And createAnd() {
		AndImpl and = new AndImpl();
		return and;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Or createOr() {
		OrImpl or = new OrImpl();
		return or;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Impl createImpl() {
		ImplImpl impl = new ImplImpl();
		return impl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Not createNot() {
		NotImpl not = new NotImpl();
		return not;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Iff createIff() {
		IffImpl iff = new IffImpl();
		return iff;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Equals createEquals() {
		EqualsImpl equals = new EqualsImpl();
		return equals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Distinct createDistinct() {
		DistinctImpl distinct = new DistinctImpl();
		return distinct;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LessThan createLessThan() {
		LessThanImpl lessThan = new LessThanImpl();
		return lessThan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MoreThan createMoreThan() {
		MoreThanImpl moreThan = new MoreThanImpl();
		return moreThan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LessOrEqualThan createLessOrEqualThan() {
		LessOrEqualThanImpl lessOrEqualThan = new LessOrEqualThanImpl();
		return lessOrEqualThan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MoreOrEqualThan createMoreOrEqualThan() {
		MoreOrEqualThanImpl moreOrEqualThan = new MoreOrEqualThanImpl();
		return moreOrEqualThan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Plus createPlus() {
		PlusImpl plus = new PlusImpl();
		return plus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Minus createMinus() {
		MinusImpl minus = new MinusImpl();
		return minus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Multiply createMultiply() {
		MultiplyImpl multiply = new MultiplyImpl();
		return multiply;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Divison createDivison() {
		DivisonImpl divison = new DivisonImpl();
		return divison;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Mod createMod() {
		ModImpl mod = new ModImpl();
		return mod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Assertion createAssertion() {
		AssertionImpl assertion = new AssertionImpl();
		return assertion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConstantDefinition createConstantDefinition() {
		ConstantDefinitionImpl constantDefinition = new ConstantDefinitionImpl();
		return constantDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RelationDefinition createRelationDefinition() {
		RelationDefinitionImpl relationDefinition = new RelationDefinitionImpl();
		return relationDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FunctionDefinition createFunctionDefinition() {
		FunctionDefinitionImpl functionDefinition = new FunctionDefinitionImpl();
		return functionDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IfThenElse createIfThenElse() {
		IfThenElseImpl ifThenElse = new IfThenElseImpl();
		return ifThenElse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConstantDeclaration createConstantDeclaration() {
		ConstantDeclarationImpl constantDeclaration = new ConstantDeclarationImpl();
		return constantDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RelationDeclaration createRelationDeclaration() {
		RelationDeclarationImpl relationDeclaration = new RelationDeclarationImpl();
		return relationDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FunctionDeclaration createFunctionDeclaration() {
		FunctionDeclarationImpl functionDeclaration = new FunctionDeclarationImpl();
		return functionDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TypeDeclaration createTypeDeclaration() {
		TypeDeclarationImpl typeDeclaration = new TypeDeclarationImpl();
		return typeDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public UnknownBecauseUninterpreted createUnknownBecauseUninterpreted() {
		UnknownBecauseUninterpretedImpl unknownBecauseUninterpreted = new UnknownBecauseUninterpretedImpl();
		return unknownBecauseUninterpreted;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InstanceOf createInstanceOf() {
		InstanceOfImpl instanceOf = new InstanceOfImpl();
		return instanceOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StringTypeReference createStringTypeReference() {
		StringTypeReferenceImpl stringTypeReference = new StringTypeReferenceImpl();
		return stringTypeReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StringLiteral createStringLiteral() {
		StringLiteralImpl stringLiteral = new StringLiteralImpl();
		return stringLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TransitiveClosure createTransitiveClosure() {
		TransitiveClosureImpl transitiveClosure = new TransitiveClosureImpl();
		return transitiveClosure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Pow createPow() {
		PowImpl pow = new PowImpl();
		return pow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Sum createSum() {
		SumImpl sum = new SumImpl();
		return sum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Count createCount() {
		CountImpl count = new CountImpl();
		return count;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Min createMin() {
		MinImpl min = new MinImpl();
		return min;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Max createMax() {
		MaxImpl max = new MaxImpl();
		return max;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LogiclanguagePackage getLogiclanguagePackage() {
		return (LogiclanguagePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static LogiclanguagePackage getPackage() {
		return LogiclanguagePackage.eINSTANCE;
	}

} //LogiclanguageFactoryImpl
