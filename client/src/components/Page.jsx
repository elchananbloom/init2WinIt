

const Page = ({ children }) => {
    return (
        <>
            <div className="border content-area-wrapper bg-light shadow">
                <div className="content-area">

                    {children}
                </div>
            </div>
        </>
    );

}

export default Page;